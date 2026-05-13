package com.moviebooking.servlet;

import com.moviebooking.service.AuthService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin", "/admin/*"})
public class AdminAuthFilter implements Filter {
    private final AuthService authService = new AuthService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if (!authService.isLoggedIn(session)) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        if (!authService.isAdmin(session)) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.setContentType("text/html;charset=UTF-8");
            httpResponse.getWriter().write("<!DOCTYPE html><html><head><title>Forbidden</title></head>"
                    + "<body style=\"background:#111;color:#fff;font-family:Arial,sans-serif;text-align:center;padding:60px;\">"
                    + "<h1 style=\"color:#dc3545;\">403 Forbidden</h1>"
                    + "<p>You are not authorized to access this page.</p>"
                    + "<a style=\"color:#dc3545;\" href=\"" + httpRequest.getContextPath() + "/movies\">Back to movies</a>"
                    + "</body></html>");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
