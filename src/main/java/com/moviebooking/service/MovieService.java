package com.moviebooking.service;

import com.moviebooking.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private static final List<Movie> MOVIES = new ArrayList<Movie>();

    static {
        MOVIES.add(new Movie("M001", "Red Horizon", "A pilot races to stop a midnight launch.",
                "Action", 8.7, 132, 1800.00, "https://placehold.co/420x620/151515/dc3545?text=Red+Horizon",
                "https://placehold.co/1400x520/090909/dc3545?text=Red+Horizon", "PG-13"));
        MOVIES.add(new Movie("M002", "Midnight Reel", "A lost cinema print reveals tomorrow's headlines.",
                "Mystery", 8.3, 118, 1500.00, "https://placehold.co/420x620/151515/ffffff?text=Midnight+Reel",
                "https://placehold.co/1400x520/111111/ffffff?text=Midnight+Reel", "PG"));
        MOVIES.add(new Movie("M003", "Neon Avenue", "Two musicians chase one perfect performance.",
                "Drama", 7.9, 126, 1600.00, "https://placehold.co/420x620/151515/dc3545?text=Neon+Avenue",
                "https://placehold.co/1400x520/101010/dc3545?text=Neon+Avenue", "PG-13"));
    }

    public synchronized List<Movie> getAllMovies() {
        return new ArrayList<Movie>(MOVIES);
    }

    public synchronized Movie getMovieById(String id) {
        if (id == null) {
            return null;
        }
        for (Movie movie : MOVIES) {
            if (movie.getId().equalsIgnoreCase(id.trim())) {
                return movie;
            }
        }
        return null;
    }

    public synchronized void addMovie(Movie movie) {
        if (movie != null) {
            MOVIES.add(movie);
        }
    }

    public synchronized void updateMovie(Movie movie) {
        if (movie == null || movie.getId() == null) {
            return;
        }
        for (int i = 0; i < MOVIES.size(); i++) {
            if (MOVIES.get(i).getId().equalsIgnoreCase(movie.getId())) {
                MOVIES.set(i, movie);
                return;
            }
        }
    }

    public synchronized void deleteMovie(String id) {
        Movie movie = getMovieById(id);
        if (movie != null) {
            MOVIES.remove(movie);
        }
    }
}
