package com.moviebooking.servlet;

import com.moviebooking.model.User;
import com.moviebooking.model.UserRole;
import com.moviebooking.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = authService.validateCredentials(username, password);

        if (user == null) {
            request.setAttribute("error", "Invalid username or password.");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute(AuthService.SESSION_USER_KEY, user);

        String contextPath = request.getContextPath();
        if (UserRole.ADMIN.equals(user.getRole())) {
            response.sendRedirect(contextPath + "/admin");
        } else {
            response.sendRedirect(contextPath + "/movies");
        }
    }
}
