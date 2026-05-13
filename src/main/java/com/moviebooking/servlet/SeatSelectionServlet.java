package com.moviebooking.servlet;

import com.moviebooking.model.SeatMap;
import com.moviebooking.model.Showtime;
import com.moviebooking.service.ShowtimeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/seats")
public class SeatSelectionServlet extends HttpServlet {
    private final ShowtimeService showtimeService = new ShowtimeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showSeatSelection(request, response, null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String showtimeId = request.getParameter("showtimeId");
        Showtime showtime = showtimeService.getShowtimeById(showtimeId);
        String[] selectedSeatValues = request.getParameterValues("selectedSeats");

        if (showtime == null) {
            showSeatSelection(request, response, "Showtime not found.");
            return;
        }

        if (selectedSeatValues == null || selectedSeatValues.length == 0) {
            showSeatSelection(request, response, "Please select at least one seat.");
            return;
        }

        List<String> selectedSeats = new ArrayList<String>(Arrays.asList(selectedSeatValues));
        HttpSession session = request.getSession(true);
        releasePreviousHeldSeats(session);

        SeatMap seatMap = showtime.getSeatMap();
        if (!seatMap.holdSeats(selectedSeats)) {
            showSeatSelection(request, response, "One or more selected seats are no longer available.");
            return;
        }

        session.setAttribute("selectedShowtimeId", showtimeId);
        session.setAttribute("selectedSeats", selectedSeats);
        response.sendRedirect(request.getContextPath() + "/checkout");
    }

    private void showSeatSelection(HttpServletRequest request, HttpServletResponse response, String error)
            throws ServletException, IOException {
        String showtimeId = request.getParameter("showtimeId");
        Showtime showtime = showtimeService.getShowtimeById(showtimeId);

        if (showtime == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("message", "Showtime not found.");
        } else {
            request.setAttribute("movieTitle", showtimeService.getMovieTitle(showtime.getMovieId()));
        }

        request.setAttribute("showtime", showtime);
        request.setAttribute("error", error);
        request.getRequestDispatcher("/WEB-INF/views/seat-selection.jsp").forward(request, response);
    }

    @SuppressWarnings("unchecked")
    private void releasePreviousHeldSeats(HttpSession session) {
        Object previousShowtimeId = session.getAttribute("selectedShowtimeId");
        Object previousSeats = session.getAttribute("selectedSeats");

        if (!(previousShowtimeId instanceof String) || !(previousSeats instanceof List)) {
            return;
        }

        SeatMap previousSeatMap = showtimeService.getSeatMap((String) previousShowtimeId);
        if (previousSeatMap != null) {
            previousSeatMap.releaseSeats((List<String>) previousSeats);
        }
    }
}
