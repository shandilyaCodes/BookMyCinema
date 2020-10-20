package com.shandilya.movie.controller;

import com.shandilya.movie.dto.PaymentDTO;
import com.shandilya.movie.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
@RequiredArgsConstructor
public class PaymentsController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDTO> payForBooking(@RequestBody PaymentDTO paymentDTO) {
        final PaymentDTO paid = paymentService.pay(paymentDTO);
        return new ResponseEntity<>(paid, HttpStatus.OK);
    }

    @PostMapping("/retry")
    public ResponseEntity<PaymentDTO> retryPayment(@RequestBody PaymentDTO paymentDTO) {
        final PaymentDTO paid = paymentService.retryPayment(paymentDTO);
        return new ResponseEntity<>(paid, HttpStatus.OK);
    }
}