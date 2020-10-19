package com.shandilya.movie.exceptions;

public class SeatTemporarilyUnavailableException extends RuntimeException {
    public SeatTemporarilyUnavailableException(String message) {
        super(message);
    }
}
