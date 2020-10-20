package com.shandilya.movie.repository;

import com.shandilya.movie.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findPaymentsByBookingId(Long bookingId);
}