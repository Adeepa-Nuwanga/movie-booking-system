package com.moviebooking.servlet;

import com.moviebooking.model.Showtime;
import com.moviebooking.service.ShowtimeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/showtimes")
public class ShowtimeServlet extends HttpServlet {
    private final ShowtimeService showtimeService = new ShowtimeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String movieId = request.getParameter("movieId");
        List<Showtime> showtimes = showtimeService.getShowtimesByMovieId(movieId);

        request.setAttribute("movieId", movieId);
        request.setAttribute("movieTitle", showtimeService.getMovieTitle(movieId));
        request.setAttribute("showtimes", showtimes);
        request.getRequestDispatcher("/WEB-INF/views/showtimes.jsp").forward(request, response);
    }
}
