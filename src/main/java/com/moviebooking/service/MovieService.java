package com.moviebooking.service;

import com.moviebooking.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private static final ArrayList<Movie> MOVIES = new ArrayList<Movie>();

    static {
        MOVIES.add(new Movie(
                "M001",
                "Red Horizon",
                "A daring pilot races through a city under siege to stop a final midnight launch.",
                "Action",
                8.7,
                132,
                1800.00,
                "https://placehold.co/420x620/151515/dc3545?text=Red+Horizon",
                "https://placehold.co/1400x520/090909/dc3545?text=Red+Horizon",
                "PG-13"));
        MOVIES.add(new Movie(
                "M002",
                "Midnight Reel",
                "A film archivist discovers that a lost cinema print reveals tomorrow's headlines.",
                "Mystery",
                8.3,
                118,
                1500.00,
                "https://placehold.co/420x620/151515/ffffff?text=Midnight+Reel",
                "https://placehold.co/1400x520/111111/ffffff?text=Midnight+Reel",
                "PG"));
        MOVIES.add(new Movie(
                "M003",
                "Neon Avenue",
                "Two musicians chase one perfect performance across a rain-lit city of rival stages.",
                "Drama",
                7.9,
                126,
                1600.00,
                "https://placehold.co/420x620/151515/dc3545?text=Neon+Avenue",
                "https://placehold.co/1400x520/101010/dc3545?text=Neon+Avenue",
                "PG-13"));
        MOVIES.add(new Movie(
                "M004",
                "Galaxy Gate",
                "A family adventure begins when an old observatory opens a door beyond the stars.",
                "Sci-Fi",
                8.9,
                144,
                2200.00,
                "https://placehold.co/420x620/151515/ffffff?text=Galaxy+Gate",
                "https://placehold.co/1400x520/090909/ffffff?text=Galaxy+Gate",
                "PG"));
        MOVIES.add(new Movie(
                "M005",
                "The Last Interval",
                "A retiring theatre manager fights to save the cinema that shaped his whole town.",
                "Family",
                7.6,
                104,
                1200.00,
                "https://placehold.co/420x620/151515/dc3545?text=Last+Interval",
                "https://placehold.co/1400x520/111111/dc3545?text=The+Last+Interval",
                "G"));
        MOVIES.add(new Movie(
                "M006",
                "Crimson Case",
                "A detective follows one red ticket stub through a chain of impossible clues.",
                "Thriller",
                8.1,
                112,
                1700.00,
                "https://placehold.co/420x620/151515/ffffff?text=Crimson+Case",
                "https://placehold.co/1400x520/101010/ffffff?text=Crimson+Case",
                "R"));
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<Movie>(MOVIES);
    }

    public Movie getMovieById(String id) {
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

    public void addMovie(Movie movie) {
        if (movie != null) {
            MOVIES.add(movie);
        }
    }

    public void updateMovie(Movie movie) {
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

    public void deleteMovie(String id) {
        Movie movie = getMovieById(id);
        if (movie != null) {
            MOVIES.remove(movie);
        }
    }

    public List<Movie> getSortedMovies(String sortBy) {
        ArrayList<Movie> sortedMovies = new ArrayList<Movie>(MOVIES);
        if (!isSupportedSort(sortBy)) {
            return sortedMovies;
        }

        for (int i = 1; i < sortedMovies.size(); i++) {
            Movie currentMovie = sortedMovies.get(i);
            int j = i - 1;

            while (j >= 0 && shouldMoveRight(sortedMovies.get(j), currentMovie, sortBy)) {
                sortedMovies.set(j + 1, sortedMovies.get(j));
                j--;
            }

            sortedMovies.set(j + 1, currentMovie);
        }

        return sortedMovies;
    }

    private boolean isSupportedSort(String sortBy) {
        return "rating".equals(sortBy)
                || "price".equals(sortBy)
                || "duration".equals(sortBy)
                || "title".equals(sortBy);
    }

    private boolean shouldMoveRight(Movie left, Movie current, String sortBy) {
        if ("rating".equals(sortBy)) {
            return left.getRating() < current.getRating();
        }

        if ("price".equals(sortBy)) {
            return left.getPrice() > current.getPrice();
        }

        if ("duration".equals(sortBy)) {
            return left.getDurationMinutes() > current.getDurationMinutes();
        }

        if ("title".equals(sortBy)) {
            return left.getTitle().compareToIgnoreCase(current.getTitle()) > 0;
        }

        return false;
    }
}
