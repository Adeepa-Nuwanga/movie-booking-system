package com.moviebooking.service;

import com.moviebooking.model.Booking;
import com.moviebooking.model.BookingStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ReceiptBuilder {
    public Booking buildDemoBooking() {
        List<String> seats = Arrays.asList("C3", "C4");
        return new Booking(
                "BKG-DEMO-001",
                "REQ-DEMO-001",
                "Demo Customer",
                BookingHistoryService.DEMO_USER_KEY,
                "Red Horizon",
                "2026-05-14",
                "07:00 PM",
                "Hall A",
                seats,
                3000.00,
                LocalDateTime.now().minusDays(1),
                BookingStatus.CONFIRMED);
    }
}
