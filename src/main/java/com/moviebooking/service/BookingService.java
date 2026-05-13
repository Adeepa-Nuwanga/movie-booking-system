package com.moviebooking.service;

import com.moviebooking.model.Booking;
import com.moviebooking.model.BookingRequest;
import com.moviebooking.model.BookingStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BookingService {
    public static final double SEAT_PRICE = 1500.00;

    private static final Queue<BookingRequest> bookingQueue = new LinkedList<BookingRequest>();
    private static final List<BookingRequest> processedRequests = new ArrayList<BookingRequest>();
    private static final List<Booking> bookings = new ArrayList<Booking>();
    private static final Map<String, Set<String>> bookedSeatsByShowtime = new HashMap<String, Set<String>>();
    private static BookingRequest lastProcessed;
    private static int nextRequestNumber = 1;
    private static int nextBookingNumber = 1;

    public synchronized BookingRequest createRequest(String customerName, String customerEmail, String movieId,
                                                     String showtimeId, List<String> selectedSeats) {
        String requestId = "REQ" + String.format("%04d", nextRequestNumber++);
        double totalPrice = selectedSeats.size() * SEAT_PRICE;
        return new BookingRequest(requestId, clean(customerName), clean(customerEmail), clean(movieId),
                clean(showtimeId), selectedSeats, totalPrice);
    }

    public synchronized void enqueueBooking(BookingRequest request) {
        if (request != null) {
            bookingQueue.offer(request);
        }
    }

    public synchronized BookingRequest processNext() {
        BookingRequest request = bookingQueue.poll();
        if (request == null) {
            return null;
        }

        if (validateSeats(request.getShowtimeId(), request.getSelectedSeats())) {
            bookSeats(request.getShowtimeId(), request.getSelectedSeats());
            request.confirm();
            bookings.add(new Booking("BKG" + String.format("%04d", nextBookingNumber++), request));
        } else {
            request.reject("One or more selected seats are no longer available.");
        }

        processedRequests.add(request);
        lastProcessed = request;
        return request;
    }

    public synchronized boolean validateSeats(String showtimeId, List<String> selectedSeats) {
        if (isBlank(showtimeId) || selectedSeats == null || selectedSeats.isEmpty()) {
            return false;
        }

        Set<String> bookedSeats = getBookedSeats(showtimeId);
        for (String seat : selectedSeats) {
            if (isBlank(seat) || bookedSeats.contains(seat.trim().toUpperCase())) {
                return false;
            }
        }

        return true;
    }

    public synchronized int getQueueSize() {
        return bookingQueue.size();
    }

    public synchronized List<BookingRequest> getPendingRequests() {
        return new ArrayList<BookingRequest>(bookingQueue);
    }

    public synchronized List<BookingRequest> getProcessedRequests() {
        return new ArrayList<BookingRequest>(processedRequests);
    }

    public synchronized BookingRequest getLastProcessed() {
        return lastProcessed;
    }

    public synchronized BookingRequest findRequestById(String requestId) {
        if (requestId == null) {
            return null;
        }

        for (BookingRequest request : processedRequests) {
            if (request.getRequestId().equalsIgnoreCase(requestId.trim())) {
                return request;
            }
        }

        for (BookingRequest request : bookingQueue) {
            if (request.getRequestId().equalsIgnoreCase(requestId.trim())) {
                return request;
            }
        }

        return null;
    }

    public synchronized List<Booking> getBookings() {
        return new ArrayList<Booking>(bookings);
    }

    private void bookSeats(String showtimeId, List<String> selectedSeats) {
        Set<String> bookedSeats = getBookedSeats(showtimeId);
        for (String seat : selectedSeats) {
            bookedSeats.add(seat.trim().toUpperCase());
        }
        bookedSeatsByShowtime.put(showtimeId.trim().toUpperCase(), bookedSeats);
    }

    private Set<String> getBookedSeats(String showtimeId) {
        String key = showtimeId.trim().toUpperCase();
        Set<String> bookedSeats = bookedSeatsByShowtime.get(key);
        if (bookedSeats == null) {
            bookedSeats = new HashSet<String>();
            bookedSeatsByShowtime.put(key, bookedSeats);
        }
        return bookedSeats;
    }

    private String clean(String value) {
        return value == null ? "" : value.trim();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
