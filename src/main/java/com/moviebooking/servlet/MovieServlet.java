package com.moviebooking.servlet;

import com.moviebooking.model.Movie;
import com.moviebooking.service.MovieService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/movies")
public class MovieServlet extends HttpServlet {
    private final MovieService movieService = new MovieService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sortBy = request.getParameter("sort");
        List<Movie> movies = movieService.getSortedMovies(sortBy);

        request.setAttribute("movies", movies);
        request.setAttribute("selectedSort", sortBy == null ? "" : sortBy);
        request.getRequestDispatcher("/WEB-INF/views/movies.jsp").forward(request, response);
    }
}
