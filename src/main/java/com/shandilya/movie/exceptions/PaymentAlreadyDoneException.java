package com.shandilya.movie.exceptions;

public class PaymentAlreadyDoneException extends RuntimeException {
    public PaymentAlreadyDoneException(String message) {
        super(message);
    }
}
