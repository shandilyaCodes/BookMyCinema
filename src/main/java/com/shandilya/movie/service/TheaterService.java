package com.shandilya.movie.service;

import com.shandilya.movie.exceptions.NotFoundException;
import com.shandilya.movie.model.Screen;
import com.shandilya.movie.model.Seat;
import com.shandilya.movie.model.Theater;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TheaterService {
    private final Map<String, Theater> theaters;
    private final Map<String, Screen> screens;
    private final Map<String, Seat> seats;

    public TheaterService() {
        this.theaters = new HashMap<>();
        this.screens = new HashMap<>();
        this.seats = new HashMap<>();
    }

    public Seat getSeat(String seatId) {
        if (!seats.containsKey(seatId)) {
            throw new NotFoundException("Seat Not Found!");
        }
        return seats.get(seatId);
    }

    public Theater getTheater(String theaterId) {
        if (!theaters.containsKey(theaterId)) {
            throw new NotFoundException("Theater Not Found!");
        }
        return theaters.get(theaterId);
    }

    public Screen getScreen(String screenId) {
        if (!screens.containsKey(screenId)) {
            throw new NotFoundException("Screen Not Found!");
        }
        return screens.get(screenId);
    }

    public Theater createTheater(String theaterName) {
        final String theaterId = UUID.randomUUID().toString();
        Theater theater = new Theater(theaterId, theaterName);
        theaters.put(theaterId, theater);
        return theater;
    }

    public Screen createScreenInTheater(String screenName, Theater theater) {
        Screen screen = createScreen(screenName, theater);
        theater.addScreen(screen);
        return screen;
    }

    public Seat createSeatInScreen(Integer rowNo, Integer seatNo, Screen screen) {
        final String seatId = UUID.randomUUID().toString();
        Seat seat = new Seat(seatId, rowNo, seatNo);
        seats.put(seatId, seat);
        screen.addSeat(seat);
        return seat;
    }

    private Screen createScreen(String screenName, Theater theater) {
        final String screenId = UUID.randomUUID().toString();
        final Screen screen = new Screen(screenId, screenName, theater);
        screens.put(screenId, screen);
        return screen;
    }
}