package com.moviebooking.servlet;

import com.moviebooking.model.BookingRequest;
import com.moviebooking.service.BookingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/booking-result")
public class BookingResultServlet extends HttpServlet {
    private final BookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestId = request.getParameter("requestId");
        BookingRequest bookingRequest = bookingService.findRequestById(requestId);

        if (bookingRequest == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("message", "Booking request not found.");
        }

        request.setAttribute("bookingRequest", bookingRequest);
        request.getRequestDispatcher("/WEB-INF/views/booking-result.jsp").forward(request, response);
    }
}
