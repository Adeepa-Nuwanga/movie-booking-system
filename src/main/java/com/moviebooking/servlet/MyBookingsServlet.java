package com.moviebooking.servlet;

import com.moviebooking.service.BookingHistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/my-bookings")
public class MyBookingsServlet extends HttpServlet {
    private final BookingHistoryService bookingHistoryService = new BookingHistoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userKey = resolveUserKey(request.getSession(false));
        request.setAttribute("userKey", userKey);
        request.setAttribute("bookings", bookingHistoryService.getBookingsForUser(userKey));
        request.getRequestDispatcher("/WEB-INF/views/my-bookings.jsp").forward(request, response);
    }

    private String resolveUserKey(HttpSession session) {
        if (session == null) {
            return BookingHistoryService.DEMO_USER_KEY;
        }

        Object customerEmail = session.getAttribute("customerEmail");
        if (customerEmail instanceof String && !((String) customerEmail).trim().isEmpty()) {
            return (String) customerEmail;
        }

        Object userEmail = session.getAttribute("userEmail");
        if (userEmail instanceof String && !((String) userEmail).trim().isEmpty()) {
            return (String) userEmail;
        }

        return BookingHistoryService.DEMO_USER_KEY;
    }
}
