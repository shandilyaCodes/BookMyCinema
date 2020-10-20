package com.shandilya.movie.exceptions;

public class AudiNotFoundException extends RuntimeException {
    public AudiNotFoundException(String message) {
        super(message);
    }
}