package com.shandilya.movie.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Screen {

    private final String screenId;
    private final String name;
    private final Theater theater;
    private final List<Seat> seats;

    public Screen(String screenId, String name, Theater theater) {
        this.screenId = screenId;
        this.name = name;
        this.theater = theater;
        seats = new ArrayList<>();
    }

    public void addSeat(@NonNull final Seat seat) {
        this.seats.add(seat);
    }
}