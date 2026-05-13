package com.moviebooking.service;

import com.moviebooking.model.SeatMap;
import com.moviebooking.model.Showtime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowtimeService {
    private static final List<Showtime> SHOWTIMES = new ArrayList<Showtime>();
    private static final Map<String, String> MOVIE_TITLES = new HashMap<String, String>();

    static {
        seedMovieTitles();
        seedShowtimes();
    }

    public List<Showtime> getShowtimesByMovieId(String movieId) {
        List<Showtime> matchingShowtimes = new ArrayList<Showtime>();
        if (movieId == null) {
            return matchingShowtimes;
        }

        for (Showtime showtime : SHOWTIMES) {
            if (showtime.getMovieId().equalsIgnoreCase(movieId.trim())) {
                matchingShowtimes.add(showtime);
            }
        }

        return matchingShowtimes;
    }

    public Showtime getShowtimeById(String showtimeId) {
        if (showtimeId == null) {
            return null;
        }

        for (Showtime showtime : SHOWTIMES) {
            if (showtime.getId().equalsIgnoreCase(showtimeId.trim())) {
                return showtime;
            }
        }

        return null;
    }

    public SeatMap getSeatMap(String showtimeId) {
        Showtime showtime = getShowtimeById(showtimeId);
        return showtime == null ? null : showtime.getSeatMap();
    }

    public String getMovieTitle(String movieId) {
        if (movieId == null) {
            return "Selected Movie";
        }

        String title = MOVIE_TITLES.get(movieId.trim().toUpperCase());
        return title == null ? movieId : title;
    }

    private static void seedMovieTitles() {
        MOVIE_TITLES.put("M001", "Red Horizon");
        MOVIE_TITLES.put("M002", "Midnight Reel");
        MOVIE_TITLES.put("M003", "Neon Avenue");
        MOVIE_TITLES.put("M004", "Galaxy Gate");
        MOVIE_TITLES.put("M005", "The Last Interval");
        MOVIE_TITLES.put("M006", "Crimson Case");
    }

    private static void seedShowtimes() {
        SHOWTIMES.add(createShowtime("ST001", "M001", "Hall A", "2026-05-14", "10:30 AM",
                Arrays.asList("A1", "A2", "C4", "D6")));
        SHOWTIMES.add(createShowtime("ST002", "M001", "Hall B", "2026-05-14", "07:00 PM",
                Arrays.asList("B3", "B4", "E7")));
        SHOWTIMES.add(createShowtime("ST003", "M002", "Hall A", "2026-05-15", "02:15 PM",
                Arrays.asList("A5", "C2", "C3")));
        SHOWTIMES.add(createShowtime("ST004", "M003", "Hall C", "2026-05-15", "06:45 PM",
                Arrays.asList("F1", "F2", "D4")));
        SHOWTIMES.add(createShowtime("ST005", "M004", "IMAX Hall", "2026-05-16", "08:30 PM",
                Arrays.asList("A1", "A2", "A3", "B6")));
        SHOWTIMES.add(createShowtime("ST006", "M005", "Hall B", "2026-05-16", "11:00 AM",
                Arrays.asList("C5", "D5")));
        SHOWTIMES.add(createShowtime("ST007", "M006", "Hall C", "2026-05-17", "09:15 PM",
                Arrays.asList("E1", "E2", "E3")));
    }

    private static Showtime createShowtime(String id, String movieId, String cinemaHall, String date,
                                           String time, List<String> bookedSeats) {
        SeatMap seatMap = new SeatMap(6, 8);
        seatMap.bookSeats(bookedSeats);
        return new Showtime(id, movieId, cinemaHall, date, time, seatMap);
    }
}
