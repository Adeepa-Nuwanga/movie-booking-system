package com.moviebooking.service;

import com.moviebooking.model.User;
import com.moviebooking.model.UserRole;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuthService {
    public static final String SESSION_USER_KEY = "loggedUser";

    private static final List<User> USERS = new ArrayList<User>();

    static {
        USERS.add(new User(1, "Administrator", "admin", "admin123", UserRole.ADMIN));
        USERS.add(new User(2, "Demo User", "user", "user123", UserRole.USER));
    }

    public User validateCredentials(String username, String password) {
        if (username == null || password == null) {
            return null;
        }

        String normalizedUsername = username.trim();
        for (User user : USERS) {
            if (user.getUsername().equalsIgnoreCase(normalizedUsername)
                    && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public User getCurrentUser(HttpSession session) {
        if (session == null) {
            return null;
        }

        Object user = session.getAttribute(SESSION_USER_KEY);
        if (user instanceof User) {
            return (User) user;
        }

        return null;
    }

    public boolean isLoggedIn(HttpSession session) {
        return getCurrentUser(session) != null;
    }

    public boolean isAdmin(HttpSession session) {
        User user = getCurrentUser(session);
        return user != null && UserRole.ADMIN.equals(user.getRole());
    }

    public void logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(USERS);
    }
}
