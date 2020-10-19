package com.shandilya.movie.service;

import com.shandilya.movie.exceptions.BadRequestException;
import com.shandilya.movie.locker.ISeatLocker;
import com.shandilya.movie.model.Booking;

import javax.xml.stream.FactoryConfigurationError;
import java.util.HashMap;
import java.util.Map;

public class PaymentService {
    Map<Booking, Integer> bookingFailures;
    private final Integer allowedRetries;
    private final ISeatLocker seatLocker;

    public PaymentService(Integer allowedRetries, ISeatLocker seatLocker) {
        this.allowedRetries = allowedRetries;
        this.seatLocker = seatLocker;
        bookingFailures = new HashMap<>();
    }

    public void processFailedPayment(Booking booking, String user) {
        if (!booking.getUser().equals(user)) {
            throw new BadRequestException("Bad Request");
        }
        if (!bookingFailures.containsKey(booking)) {
            bookingFailures.put(booking, 0);
        }
        final Integer currentFailureCount = bookingFailures.get(booking);
        final int updatedFailureCount = currentFailureCount + 1;
        bookingFailures.put(booking,updatedFailureCount);
        if (updatedFailureCount > allowedRetries) {
            seatLocker.unlockSeats(booking.getShow(), booking.getBookedSeats(), booking.getUser());
        }
    }
}