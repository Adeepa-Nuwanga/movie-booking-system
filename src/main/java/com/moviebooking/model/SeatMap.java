package com.moviebooking.model;

public class SeatMap {
    private int rows;
    private int columns;

    public SeatMap(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getTotalSeats() {
        return rows * columns;
    }
}
