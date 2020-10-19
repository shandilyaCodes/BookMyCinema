package com.shandilya.movie.controller;

import com.shandilya.movie.service.BookingService;
import com.shandilya.movie.service.PaymentService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaymentsController {

    private final PaymentService paymentService;
    private final BookingService bookingService;

    public void paymentFailed(String bookingId, String user) {
        paymentService.processFailedPayment(bookingService.getBooking(bookingId), user);
    }

    public void paymentSuccess(String bookingId, String user) {
        bookingService.confirmBooking(bookingService.getBooking(bookingId), user);
    }
}