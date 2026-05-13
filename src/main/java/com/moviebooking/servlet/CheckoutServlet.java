package com.moviebooking.servlet;

import com.moviebooking.model.BookingRequest;
import com.moviebooking.service.BookingService;

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

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private final BookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        prepareCheckoutSummary(request);
        request.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String showtimeId = getSessionString(session, "selectedShowtimeId");
        String movieId = getSessionString(session, "selectedMovieId");
        List<String> selectedSeats = getSelectedSeats(session);

        if (showtimeId == null || selectedSeats.isEmpty()) {
            request.setAttribute("error", "Please select seats before confirming your booking.");
            prepareCheckoutSummary(request);
            request.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(request, response);
            return;
        }

        BookingRequest bookingRequest = bookingService.createRequest(
                request.getParameter("customerName"),
                request.getParameter("customerEmail"),
                movieId,
                showtimeId,
                selectedSeats);

        bookingService.enqueueBooking(bookingRequest);
        BookingRequest processedRequest = bookingService.processNext();

        session.removeAttribute("selectedShowtimeId");
        session.removeAttribute("selectedSeats");
        response.sendRedirect(request.getContextPath() + "/booking-result?requestId=" + processedRequest.getRequestId());
    }

    private void prepareCheckoutSummary(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String showtimeId = getSessionString(session, "selectedShowtimeId");
        String movieId = getSessionString(session, "selectedMovieId");
        List<String> selectedSeats = getSelectedSeats(session);
        double totalPrice = selectedSeats.size() * BookingService.SEAT_PRICE;

        if (showtimeId == null || selectedSeats.isEmpty()) {
            request.setAttribute("error", "No selected seats were found. Please choose seats before checkout.");
        }

        request.setAttribute("movieId", movieId == null ? "Selected Movie" : movieId);
        request.setAttribute("showtimeId", showtimeId == null ? "Selected Showtime" : showtimeId);
        request.setAttribute("selectedSeats", selectedSeats);
        request.setAttribute("seatPrice", BookingService.SEAT_PRICE);
        request.setAttribute("totalPrice", totalPrice);
    }

    private String getSessionString(HttpSession session, String key) {
        if (session == null) {
            return null;
        }
        Object value = session.getAttribute(key);
        return value instanceof String ? (String) value : null;
    }

    private List<String> getSelectedSeats(HttpSession session) {
        List<String> selectedSeats = new ArrayList<String>();
        if (session == null) {
            return selectedSeats;
        }

        Object value = session.getAttribute("selectedSeats");
        if (value instanceof List) {
            for (Object seat : (List<?>) value) {
                selectedSeats.add(String.valueOf(seat));
            }
        } else if (value instanceof String[]) {
            selectedSeats.addAll(Arrays.asList((String[]) value));
        } else if (value instanceof String) {
            selectedSeats.add((String) value);
        }

        return selectedSeats;
    }
}
