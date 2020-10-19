package com.shandilya.movie.service;

import com.shandilya.movie.exceptions.BadRequestException;
import com.shandilya.movie.exceptions.NotFoundException;
import com.shandilya.movie.exceptions.SeatPermanentlyUnavailableException;
import com.shandilya.movie.locker.ISeatLocker;
import com.shandilya.movie.model.Booking;
import com.shandilya.movie.model.Seat;
import com.shandilya.movie.model.Show;

import java.util.*;
import java.util.stream.Collectors;

public class BookingService {

    private final Map<String, Booking> bookingMap;
    private final ISeatLocker seatLocker;

    public BookingService(ISeatLocker seatLocker) {
        this.bookingMap = new HashMap<>();
        this.seatLocker = seatLocker;
    }

    public Booking getBooking(String bookingId) {
        if (!bookingMap.containsKey(bookingId)) {
            throw new NotFoundException("Booking Not Found!");
        }
        return bookingMap.get(bookingId);
    }

    public List<Booking> getAllBookings(Show show) {
        List<Booking> bookings = new ArrayList<>();
        for (Booking booking : bookingMap.values()) {
            if (booking.getShow().equals(show)) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

    public Booking createBooking(String userId, Show show, List<Seat> seats) {
        if (isAnySeatAlreadyBooked(show, seats)) {
            throw new SeatPermanentlyUnavailableException("Seat not available!");
        }
        seatLocker.lockSeats(show, seats, userId);
        final String bookingId = UUID.randomUUID().toString();
        final Booking booking = new Booking(bookingId, show, seats, userId);
        bookingMap.put(bookingId, booking);
        return booking;
    }

    public List<Seat> getBookedSeats(Show show) {
        return getAllBookings(show).stream()
                .filter(Booking::isConfirmed)
                .map(Booking::getBookedSeats)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public void confirmBooking(Booking booking, String user) {
        if (!booking.getUser().equals(user)) {
            throw new BadRequestException("Bad Request!");
        }
        for (Seat seat : booking.getBookedSeats()) {
            if (!seatLocker.validateLock(booking.getShow(), seat, user)) {
                throw new BadRequestException("Bad Request!");
            }
        }
        booking.confirmBooking();
    }

    private boolean isAnySeatAlreadyBooked(Show show, List<Seat> seats) {
        final List<Seat> bookedSeats = getBookedSeats(show);
        for (Seat seat : seats) {
            if (bookedSeats.contains(seat)) {
                return true;
            }
        }
        return false;
    }
}