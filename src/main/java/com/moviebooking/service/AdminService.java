package com.moviebooking.service;

import com.moviebooking.model.AdminBookingSummary;
import com.moviebooking.model.Movie;
import com.moviebooking.model.Showtime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminService {
    private final MovieService movieService = new MovieService();
    private final ShowtimeService showtimeService = new ShowtimeService();
    private static final List<AdminBookingSummary> RECENT_BOOKINGS = new ArrayList<AdminBookingSummary>();

    static {
        RECENT_BOOKINGS.add(new AdminBookingSummary("BKG-DEMO-001", "Demo Customer", "Red Horizon",
                Arrays.asList("C3", "C4"), "CONFIRMED", 3000.00));
        RECENT_BOOKINGS.add(new AdminBookingSummary("BKG-DEMO-002", "A. Perera", "Midnight Reel",
                Arrays.asList("A1"), "PENDING", 1500.00));
    }

    public int getTotalMovies() {
        return movieService.getAllMovies().size();
    }

    public int getTotalShowtimes() {
        return showtimeService.getAllShowtimes().size();
    }

    public int getQueueLength() {
        return 0;
    }

    public int getRecentBookingsCount() {
        return RECENT_BOOKINGS.size();
    }

    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    public void addMovie(Movie movie) {
        movieService.addMovie(movie);
    }

    public void updateMovie(Movie movie) {
        movieService.updateMovie(movie);
    }

    public void deleteMovie(String id) {
        movieService.deleteMovie(id);
    }

    public List<Showtime> getShowtimes() {
        return showtimeService.getAllShowtimes();
    }

    public void addShowtime(Showtime showtime) {
        showtimeService.addShowtime(showtime);
    }

    public void updateShowtime(Showtime showtime) {
        showtimeService.updateShowtime(showtime);
    }

    public void deleteShowtime(String id) {
        showtimeService.deleteShowtime(id);
    }

    public List<AdminBookingSummary> getRecentBookings() {
        return new ArrayList<AdminBookingSummary>(RECENT_BOOKINGS);
    }
}
