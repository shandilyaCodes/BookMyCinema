package com.shandilya.movie.exceptions;

public class PaymentNotFound extends RuntimeException {
    public PaymentNotFound(String message) {
        super(message);
    }
}