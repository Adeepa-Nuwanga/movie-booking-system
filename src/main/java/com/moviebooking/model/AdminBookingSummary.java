package com.moviebooking.model;

import java.util.ArrayList;
import java.util.List;

public class AdminBookingSummary {
    private String bookingId;
    private String customerName;
    private String movieTitle;
    private List<String> seats;
    private String status;
    private double totalPrice;

    public AdminBookingSummary(String bookingId, String customerName, String movieTitle,
                               List<String> seats, String status, double totalPrice) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.movieTitle = movieTitle;
        this.seats = new ArrayList<String>(seats);
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public List<String> getSeats() {
        return new ArrayList<String>(seats);
    }

    public String getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
