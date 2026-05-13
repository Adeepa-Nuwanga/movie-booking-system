package com.moviebooking.servlet;

import com.moviebooking.model.Movie;
import com.moviebooking.service.MovieService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/movie-details")
public class MovieDetailsServlet extends HttpServlet {
    private final MovieService movieService = new MovieService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Movie movie = movieService.getMovieById(id);

        if (movie == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("message", "Movie not found.");
        }

        request.setAttribute("movie", movie);
        request.getRequestDispatcher("/WEB-INF/views/movie-details.jsp").forward(request, response);
    }
}
