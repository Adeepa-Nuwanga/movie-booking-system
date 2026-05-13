package com.moviebooking.servlet;

import com.moviebooking.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private final AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("totalMovies", adminService.getTotalMovies());
        request.setAttribute("totalShowtimes", adminService.getTotalShowtimes());
        request.setAttribute("queueLength", adminService.getQueueLength());
        request.setAttribute("recentBookingsCount", adminService.getRecentBookingsCount());
        request.getRequestDispatcher("/WEB-INF/views/admin-dashboard.jsp").forward(request, response);
    }
}
