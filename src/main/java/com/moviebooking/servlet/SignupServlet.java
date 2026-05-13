package com.moviebooking.servlet;

import com.moviebooking.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        request.setAttribute("name", valueOrEmpty(name));
        request.setAttribute("username", valueOrEmpty(username));
        request.setAttribute("email", valueOrEmpty(email));

        if (isBlank(confirmPassword)) {
            forwardWithError(request, response, "Confirm password is required.");
            return;
        }

        if (password == null || !password.equals(confirmPassword)) {
            forwardWithError(request, response, "Password and confirm password must match.");
            return;
        }

        try {
            authService.registerUser(name, username, password, email);
            response.sendRedirect(request.getContextPath() + "/login?registered=success");
        } catch (IllegalArgumentException exception) {
            forwardWithError(request, response, exception.getMessage());
        }
    }

    private void forwardWithError(HttpServletRequest request, HttpServletResponse response, String error)
            throws ServletException, IOException {
        request.setAttribute("error", error);
        request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
    }

    private String valueOrEmpty(String value) {
        return value == null ? "" : value;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
