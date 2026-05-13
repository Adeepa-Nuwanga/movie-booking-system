package com.moviebooking.servlet;

import com.moviebooking.model.SeatMap;
import com.moviebooking.model.Showtime;
import com.moviebooking.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/showtimes", "/admin/showtimes/update", "/admin/showtimes/delete"})
public class AdminShowtimesServlet extends HttpServlet {
    private final AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("showtimes", adminService.getShowtimes());
        request.getRequestDispatcher("/WEB-INF/views/admin-showtimes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if ("/admin/showtimes/delete".equals(path)) {
            adminService.deleteShowtime(request.getParameter("id"));
        } else if ("/admin/showtimes/update".equals(path)) {
            adminService.updateShowtime(readShowtime(request));
        } else {
            adminService.addShowtime(readShowtime(request));
        }

        response.sendRedirect(request.getContextPath() + "/admin/showtimes");
    }

    private Showtime readShowtime(HttpServletRequest request) {
        return new Showtime(
                valueOrGeneratedId(request.getParameter("id")),
                request.getParameter("movieId"),
                request.getParameter("cinemaHall"),
                request.getParameter("date"),
                request.getParameter("time"),
                new SeatMap(parseInt(request.getParameter("rows"), 6), parseInt(request.getParameter("columns"), 8)));
    }

    private String valueOrGeneratedId(String id) {
        if (id != null && !id.trim().isEmpty()) {
            return id.trim();
        }
        return "ST" + System.currentTimeMillis();
    }

    private int parseInt(String value, int fallback) {
        try {
            return Integer.parseInt(value);
        } catch (Exception exception) {
            return fallback;
        }
    }
}
