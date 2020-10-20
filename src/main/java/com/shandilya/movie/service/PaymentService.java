package com.shandilya.movie.service;

import com.shandilya.movie.constants.PaymentStatus;
import com.shandilya.movie.dto.PaymentDTO;
import com.shandilya.movie.exceptions.PaymentAlreadyDoneException;
import com.shandilya.movie.exceptions.PaymentNotFound;
import com.shandilya.movie.exceptions.PaymentSuccessException;
import com.shandilya.movie.model.Payment;
import com.shandilya.movie.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final boolean[] paymentStatus = {true, false, false, false, true, true, false, true, false, true};
    private final PaymentRepository paymentRepository;

    public PaymentDTO pay(PaymentDTO paymentDTO) {
        final Payment paymentExisting = paymentRepository.findPaymentsByBookingId(paymentDTO.getBookingId());
        if (paymentExisting != null) {
            throw new PaymentAlreadyDoneException("Payment has already been done Successfully for this Booking");
        }
        final Payment payment = paymentDTO.transform();
        payment.setPaymentStatus(simulatePayment() ? PaymentStatus.SUCCEEDED.toString() : PaymentStatus.FAILED.toString());
        final Payment saved = paymentRepository.save(payment);
        if (saved.getPaymentStatus().equals(PaymentStatus.SUCCEEDED.toString())) {
            // Move the seats related to paymentDTO.bookingID to Permanently Booked State
        }
        return saved.transform();
    }

    public PaymentDTO retryPayment(PaymentDTO paymentDTO) {
        PaymentDTO paymentDT = null;
        final Payment payment = paymentRepository.findPaymentsByBookingId(paymentDTO.getBookingId());
        if (payment == null) {
            throw new PaymentNotFound("No Payment Found For This Booking");
        }
        if (payment.getPaymentStatus().equalsIgnoreCase(PaymentStatus.SUCCEEDED.toString())) {
            throw new PaymentSuccessException("Payment has been done successfully");
        }
        final Integer retries = payment.getRetries();
        if (retries == 3) {
            // Release the lock on this bookingID

        } else {
            // Retry Payment & Update the status along with the latest Payment Status
            payment.setPaymentStatus(simulatePayment() ? PaymentStatus.SUCCEEDED.toString() : PaymentStatus.FAILED.toString());
            payment.setRetries(payment.getRetries()+1);
            final Payment save = paymentRepository.save(payment);
            paymentDT = save.transform();
            if (paymentDT.getPaymentStatus().equals(PaymentStatus.SUCCEEDED.toString())) {
                // Move the seats related to paymentDTO.bookingID to permanently booked State
            }
        }
        return paymentDT;
    }

    private boolean simulatePayment() {
        final int random = new Random().nextInt(10);
        return paymentStatus[random];
    }
}