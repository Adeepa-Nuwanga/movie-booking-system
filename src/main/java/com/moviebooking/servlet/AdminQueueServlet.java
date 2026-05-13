package com.moviebooking.servlet;

import com.moviebooking.service.AdminService;
import com.moviebooking.service.BookingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/queue")
public class AdminQueueServlet extends HttpServlet {

    private final AdminService adminService = new AdminService();
    private final BookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("queueLength", adminService.getQueueLength());

        request.setAttribute("queueSize", bookingService.getQueueSize());
        request.setAttribute("pendingRequests", bookingService.getPendingRequests());
        request.setAttribute("processedRequests", bookingService.getProcessedRequests());
        request.setAttribute("lastProcessed", bookingService.getLastProcessed());

        request.getRequestDispatcher("/WEB-INF/views/admin-queue.jsp")
                .forward(request, response);
    }
}