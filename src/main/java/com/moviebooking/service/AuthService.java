package com.moviebooking.service;

import com.moviebooking.model.User;
import com.moviebooking.model.UserRole;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuthService {
    public static final String SESSION_USER_KEY = "loggedUser";

    // Users are stored in memory for this OOP assignment demo. Data resets when the server restarts.
    private static final List<User> USERS = new ArrayList<User>();
    private static int nextUserId = 3;

    static {
        USERS.add(new User(1, "Administrator", "admin", "admin@cineflex.local", "admin123", UserRole.ADMIN));
        USERS.add(new User(2, "Demo User", "user", "user@cineflex.local", "user123", UserRole.USER));
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

    public synchronized User registerUser(String name, String username, String password, String email) {
        String normalizedName = normalize(name);
        String normalizedUsername = normalize(username);
        String normalizedEmail = normalize(email);

        if (isBlank(normalizedName)) {
            throw new IllegalArgumentException("Full name is required.");
        }

        if (isBlank(normalizedUsername)) {
            throw new IllegalArgumentException("Username is required.");
        }

        if (isBlank(normalizedEmail)) {
            throw new IllegalArgumentException("Email is required.");
        }

        if (isBlank(password)) {
            throw new IllegalArgumentException("Password is required.");
        }

        if (findByUsername(normalizedUsername) != null) {
            throw new IllegalArgumentException("Username is already taken.");
        }

        if (findByEmail(normalizedEmail) != null) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        User user = new User(nextUserId++, normalizedName, normalizedUsername, normalizedEmail, password, UserRole.USER);
        USERS.add(user);
        return user;
    }

    public User findByUsername(String username) {
        if (username == null) {
            return null;
        }

        String normalizedUsername = username.trim();
        for (User user : USERS) {
            if (user.getUsername().equalsIgnoreCase(normalizedUsername)) {
                return user;
            }
        }

        return null;
    }

    public User findByEmail(String email) {
        if (email == null) {
            return null;
        }

        String normalizedEmail = email.trim();
        for (User user : USERS) {
            if (user.getEmail() != null && user.getEmail().equalsIgnoreCase(normalizedEmail)) {
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

    private String normalize(String value) {
        return value == null ? null : value.trim();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
