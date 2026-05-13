package com.moviebooking.servlet;

import com.moviebooking.model.Movie;
import com.moviebooking.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/movies", "/admin/movies/update", "/admin/movies/delete"})
public class AdminMoviesServlet extends HttpServlet {
    private final AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("movies", adminService.getMovies());
        request.getRequestDispatcher("/WEB-INF/views/admin-movies.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if ("/admin/movies/delete".equals(path)) {
            adminService.deleteMovie(request.getParameter("id"));
        } else if ("/admin/movies/update".equals(path)) {
            adminService.updateMovie(readMovie(request));
        } else {
            adminService.addMovie(readMovie(request));
        }

        response.sendRedirect(request.getContextPath() + "/admin/movies");
    }

    private Movie readMovie(HttpServletRequest request) {
        return new Movie(
                valueOrGeneratedId(request.getParameter("id")),
                request.getParameter("title"),
                request.getParameter("description"),
                request.getParameter("genre"),
                parseDouble(request.getParameter("rating")),
                parseInt(request.getParameter("durationMinutes")),
                parseDouble(request.getParameter("price")),
                request.getParameter("posterUrl"),
                request.getParameter("bannerUrl"),
                valueOrDefault(request.getParameter("ageRating"), "PG"));
    }

    private String valueOrGeneratedId(String id) {
        if (id != null && !id.trim().isEmpty()) {
            return id.trim();
        }
        return "M" + System.currentTimeMillis();
    }

    private String valueOrDefault(String value, String fallback) {
        return value == null || value.trim().isEmpty() ? fallback : value.trim();
    }

    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception exception) {
            return 0.0;
        }
    }

    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception exception) {
            return 0;
        }
    }
}
