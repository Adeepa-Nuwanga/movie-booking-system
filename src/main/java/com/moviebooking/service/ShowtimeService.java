package com.moviebooking.service;

import com.moviebooking.model.SeatMap;
import com.moviebooking.model.Showtime;

import java.util.ArrayList;
import java.util.List;

public class ShowtimeService {
    private static final List<Showtime> SHOWTIMES = new ArrayList<Showtime>();

    static {
        SHOWTIMES.add(new Showtime("ST001", "M001", "Hall A", "2026-05-14", "10:30 AM", new SeatMap(6, 8)));
        SHOWTIMES.add(new Showtime("ST002", "M001", "Hall B", "2026-05-14", "07:00 PM", new SeatMap(6, 8)));
        SHOWTIMES.add(new Showtime("ST003", "M002", "Hall C", "2026-05-15", "02:15 PM", new SeatMap(5, 8)));
    }

    public synchronized List<Showtime> getAllShowtimes() {
        return new ArrayList<Showtime>(SHOWTIMES);
    }

    public synchronized Showtime getShowtimeById(String id) {
        if (id == null) {
            return null;
        }
        for (Showtime showtime : SHOWTIMES) {
            if (showtime.getId().equalsIgnoreCase(id.trim())) {
                return showtime;
            }
        }
        return null;
    }

    public synchronized void addShowtime(Showtime showtime) {
        if (showtime != null) {
            SHOWTIMES.add(showtime);
        }
    }

    public synchronized void updateShowtime(Showtime showtime) {
        if (showtime == null || showtime.getId() == null) {
            return;
        }
        for (int i = 0; i < SHOWTIMES.size(); i++) {
            if (SHOWTIMES.get(i).getId().equalsIgnoreCase(showtime.getId())) {
                SHOWTIMES.set(i, showtime);
                return;
            }
        }
    }

    public synchronized void deleteShowtime(String id) {
        Showtime showtime = getShowtimeById(id);
        if (showtime != null) {
            SHOWTIMES.remove(showtime);
        }
    }
}
