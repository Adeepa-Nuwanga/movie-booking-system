package com.moviebooking.servlet;

import com.moviebooking.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/bookings")
public class AdminBookingsServlet extends HttpServlet {
    private final AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bookings", adminService.getRecentBookings());
        request.setAttribute("queueLength", adminService.getQueueLength());
        request.getRequestDispatcher("/WEB-INF/views/admin-bookings.jsp").forward(request, response);
    }
}
