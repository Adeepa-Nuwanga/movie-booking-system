package com.moviebooking.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Booking {

    private String bookingId;
    private String requestId;

    private String customerName;
    private String customerEmail;

    private String movieId;
    private String movieTitle;

    private String showtimeId;
    private String showtimeDate;
    private String showtimeTime;
    private String cinemaHall;

    private List<String> seats;

    private double totalPrice;

    private LocalDateTime confirmedAt;

    private BookingStatus status;

    public Booking(String bookingId, BookingRequest request) {

        this.bookingId = bookingId;

        this.requestId = request.getRequestId();

        this.customerName = request.getCustomerName();
        this.customerEmail = request.getCustomerEmail();

        this.movieId = request.getMovieId();
        this.showtimeId = request.getShowtimeId();

        this.seats = new ArrayList<String>(request.getSelectedSeats());

        this.totalPrice = request.getTotalPrice();

        this.confirmedAt = LocalDateTime.now();

        this.status = BookingStatus.CONFIRMED;
    }

    public Booking(
            String bookingId,
            String requestId,
            String customerName,
            String customerEmail,
            String movieTitle,
            String showtimeDate,
            String showtimeTime,
            String cinemaHall,
            List<String> seats,
            double totalPrice,
            LocalDateTime confirmedAt,
            BookingStatus status) {

        this.bookingId = bookingId;
        this.requestId = requestId;

        this.customerName = customerName;
        this.customerEmail = customerEmail;

        this.movieTitle = movieTitle;

        this.showtimeDate = showtimeDate;
        this.showtimeTime = showtimeTime;
        this.cinemaHall = cinemaHall;

        this.seats = new ArrayList<String>(seats);

        this.totalPrice = totalPrice;

        this.confirmedAt = confirmedAt;

        this.status = status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(String showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getShowtimeDate() {
        return showtimeDate;
    }

    public void setShowtimeDate(String showtimeDate) {
        this.showtimeDate = showtimeDate;
    }

    public String getShowtimeTime() {
        return showtimeTime;
    }

    public void setShowtimeTime(String showtimeTime) {
        this.showtimeTime = showtimeTime;
    }

    public String getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(String cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public List<String> getSeats() {
        return new ArrayList<String>(seats);
    }

    public void setSeats(List<String> seats) {
        this.seats = new ArrayList<String>(seats);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public void cancel() {
        this.status = BookingStatus.CANCELLED;
    }
}