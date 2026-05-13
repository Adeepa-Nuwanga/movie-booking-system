package com.moviebooking.model;

import java.util.ArrayList;
import java.util.List;

public class SeatMap {

    private final Seat[][] seats;
    private int rows;
    private int columns;

    public SeatMap(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seats = new Seat[rows][columns];
        initializeSeats();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getTotalSeats() {
        return rows * columns;
    }

    public boolean isAvailable(String seatCode) {
        return SeatStatus.AVAILABLE.equals(getSeatStatus(seatCode));
    }

    public boolean holdSeats(List<String> seatCodes) {
        if (!allSeatsAvailable(seatCodes)) {
            return false;
        }

        updateSeats(seatCodes, SeatStatus.HELD);
        return true;
    }

    public void releaseSeats(List<String> seatCodes) {
        if (seatCodes == null) {
            return;
        }

        for (String seatCode : seatCodes) {
            Seat seat = findSeat(seatCode);

            if (seat != null && SeatStatus.HELD.equals(seat.getStatus())) {
                seat.setStatus(SeatStatus.AVAILABLE);
            }
        }
    }

    public boolean bookSeats(List<String> seatCodes) {
        if (seatCodes == null || seatCodes.isEmpty()) {
            return false;
        }

        for (String seatCode : seatCodes) {
            Seat seat = findSeat(seatCode);

            if (seat == null || SeatStatus.BOOKED.equals(seat.getStatus())) {
                return false;
            }
        }

        updateSeats(seatCodes, SeatStatus.BOOKED);
        return true;
    }

    public SeatStatus getSeatStatus(String seatCode) {
        Seat seat = findSeat(seatCode);
        return seat == null ? null : seat.getStatus();
    }

    public List<Seat> getAllSeats() {
        List<Seat> allSeats = new ArrayList<Seat>();

        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
                allSeats.add(seats[rowIndex][columnIndex]);
            }
        }

        return allSeats;
    }

    private void initializeSeats() {
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            String rowName = String.valueOf((char) ('A' + rowIndex));

            for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
                seats[rowIndex][columnIndex] =
                        new Seat(rowName, columnIndex + 1, SeatStatus.AVAILABLE);
            }
        }
    }

    private boolean allSeatsAvailable(List<String> seatCodes) {
        if (seatCodes == null || seatCodes.isEmpty()) {
            return false;
        }

        for (String seatCode : seatCodes) {
            if (!isAvailable(seatCode)) {
                return false;
            }
        }

        return true;
    }

    private void updateSeats(List<String> seatCodes, SeatStatus status) {
        for (String seatCode : seatCodes) {
            Seat seat = findSeat(seatCode);

            if (seat != null) {
                seat.setStatus(status);
            }
        }
    }

    private Seat findSeat(String seatCode) {
        if (seatCode == null) {
            return null;
        }

        String normalizedSeatCode = seatCode.trim().toUpperCase();

        for (Seat seat : getAllSeats()) {
            if (seat.getSeatCode().equalsIgnoreCase(normalizedSeatCode)) {
                return seat;
            }
        }

        return null;
    }
}