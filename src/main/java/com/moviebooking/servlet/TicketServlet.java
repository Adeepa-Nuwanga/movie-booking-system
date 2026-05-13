package com.moviebooking.servlet;

import com.moviebooking.model.Booking;
import com.moviebooking.service.BookingHistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ticket")
public class TicketServlet extends HttpServlet {
    private final BookingHistoryService bookingHistoryService = new BookingHistoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookingId = request.getParameter("bookingId");
        Booking booking = bookingHistoryService.getBookingById(bookingId);

        if (booking == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("message", "Ticket not found.");
        }

        request.setAttribute("booking", booking);
        request.getRequestDispatcher("/WEB-INF/views/ticket.jsp").forward(request, response);
    }
}
