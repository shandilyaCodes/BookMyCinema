package com.shandilya.movie.service;

import com.shandilya.movie.locker.ISeatLocker;
import com.shandilya.movie.model.Seat;
import com.shandilya.movie.model.Show;

import java.util.ArrayList;
import java.util.List;

public class SeatAvailabilityService {
    private final BookingService bookingService;
    private final ISeatLocker seatLocker;

    public SeatAvailabilityService(BookingService bookingService, ISeatLocker seatLocker) {
        this.bookingService = bookingService;
        this.seatLocker = seatLocker;
    }

    public List<Seat> getAvailableSeats(Show show) {
        final List<Seat> seats = show.getScreen().getSeats();
        final List<Seat> unavailableSeats = getUnavailableSeats(show);
        final ArrayList<Seat> availableSeats = new ArrayList<>(seats);
        availableSeats.removeAll(unavailableSeats);
        return availableSeats;
    }

    private List<Seat> getUnavailableSeats(Show show) {
        final List<Seat> bookedSeats = bookingService.getBookedSeats(show);
        bookedSeats.addAll(seatLocker.getLockedSeats(show));
        return bookedSeats;
    }
}