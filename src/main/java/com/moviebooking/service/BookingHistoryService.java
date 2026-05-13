package com.moviebooking.service;

import com.moviebooking.model.Booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BookingHistoryService {
    public static final String DEMO_USER_KEY = "demo@cineflex.local";

    private static final Map<String, List<Booking>> BOOKINGS_BY_USER = new HashMap<String, List<Booking>>();

    static {
        ReceiptBuilder receiptBuilder = new ReceiptBuilder();
        saveConfirmedBooking(DEMO_USER_KEY, receiptBuilder.buildDemoBooking());
    }

    public static synchronized void saveConfirmedBooking(String userKey, Booking booking) {
        if (booking == null) {
            return;
        }

        String normalizedKey = normalizeUserKey(userKey);
        List<Booking> bookings = BOOKINGS_BY_USER.get(normalizedKey);
        if (bookings == null) {
            bookings = new ArrayList<Booking>();
            BOOKINGS_BY_USER.put(normalizedKey, bookings);
        }
        bookings.add(booking);
    }

    public synchronized List<Booking> getBookingsForUser(String userKey) {
        String normalizedKey = normalizeUserKey(userKey);
        List<Booking> bookings = BOOKINGS_BY_USER.get(normalizedKey);
        if (bookings == null) {
            return new ArrayList<Booking>();
        }
        return new ArrayList<Booking>(bookings);
    }

    public synchronized Booking getBookingById(String bookingId) {
        if (bookingId == null) {
            return null;
        }

        for (List<Booking> bookings : BOOKINGS_BY_USER.values()) {
            for (Booking booking : bookings) {
                if (booking.getBookingId().equalsIgnoreCase(bookingId.trim())) {
                    return booking;
                }
            }
        }

        return null;
    }

    public synchronized boolean cancelBooking(String bookingId) {
        Booking booking = getBookingById(bookingId);
        if (booking == null) {
            return false;
        }
        booking.cancel();
        return true;
    }

    public synchronized boolean removeBooking(String bookingId) {
        if (bookingId == null) {
            return false;
        }

        for (List<Booking> bookings : BOOKINGS_BY_USER.values()) {
            Iterator<Booking> iterator = bookings.iterator();
            while (iterator.hasNext()) {
                Booking booking = iterator.next();
                if (booking.getBookingId().equalsIgnoreCase(bookingId.trim())) {
                    iterator.remove();
                    return true;
                }
            }
        }

        return false;
    }

    private static String normalizeUserKey(String userKey) {
        if (userKey == null || userKey.trim().isEmpty()) {
            return DEMO_USER_KEY;
        }
        return userKey.trim().toLowerCase();
    }
}
