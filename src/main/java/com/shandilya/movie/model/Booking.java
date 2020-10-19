package com.shandilya.movie.model;

import com.shandilya.movie.exceptions.InvalidBookingStateException;
import lombok.Getter;
import lombok.NonNull;
import java.util.List;

@Getter
public class Booking {
    private final String bookingId;
    private final Show show;
    private final List<Seat> bookedSeats;
    private final String user;
    private BookingStatus bookingStatus;

    public Booking(@NonNull final String bookingId,
                   @NonNull final Show show,
                   @NonNull final List<Seat> bookedSeats,
                   @NonNull final String user) {
        this.bookingId = bookingId;
        this.show = show;
        this.bookedSeats = bookedSeats;
        this.user = user;
        this.bookingStatus = BookingStatus.CREATED;
    }

    public boolean isConfirmed() {
        return this.bookingStatus == BookingStatus.CONFIRMED;
    }

    public void confirmBooking() {
        if (this.bookingStatus != BookingStatus.CREATED) {
            throw new InvalidBookingStateException("Invalid Booking Status");
        }
        this.bookingStatus = BookingStatus.CONFIRMED;
    }

    public void expireBooking() {
        if (this.bookingStatus != BookingStatus.CONFIRMED) {
            throw new InvalidBookingStateException("Invalid Booking Status");
        }
        this.bookingStatus = BookingStatus.EXPIRED;
    }
}