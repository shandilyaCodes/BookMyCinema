package com.shandilya.movie.exceptions;

public class SeatPermanentlyUnavailableException extends RuntimeException {
    public SeatPermanentlyUnavailableException(String message) {
        super(message);
    }
}