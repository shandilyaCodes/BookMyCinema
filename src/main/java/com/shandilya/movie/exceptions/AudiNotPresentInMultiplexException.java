package com.shandilya.movie.exceptions;

public class AudiNotPresentInMultiplexException extends RuntimeException {
    public AudiNotPresentInMultiplexException(String message) {
        super(message);
    }
}