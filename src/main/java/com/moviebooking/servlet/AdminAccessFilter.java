package com.moviebooking.servlet;

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
public class AdminAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (!isAdmin(httpRequest.getSession(false))) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.setContentType("text/html;charset=UTF-8");
            httpResponse.getWriter().write("<!DOCTYPE html><html><head><title>Forbidden</title>"
                    + "<style>body{background:#111;color:#fff;font-family:Arial,sans-serif;text-align:center;padding:60px}"
                    + "a{color:#dc3545}</style></head><body><h1>403 Forbidden</h1>"
                    + "<p>Admin access is required.</p><a href='" + httpRequest.getContextPath() + "/movies'>Back to Movies</a>"
                    + "</body></html>");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private boolean isAdmin(HttpSession session) {
        if (session == null) {
            return false;
        }

        Object isAdmin = session.getAttribute("isAdmin");
        if (Boolean.TRUE.equals(isAdmin)) {
            return true;
        }

        Object role = session.getAttribute("role");
        if (role != null && "ADMIN".equalsIgnoreCase(String.valueOf(role))) {
            return true;
        }

        Object userRole = session.getAttribute("userRole");
        return userRole != null && "ADMIN".equalsIgnoreCase(String.valueOf(userRole));
    }
}
