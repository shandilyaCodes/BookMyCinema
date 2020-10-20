package com.shandilya.movie.exceptions;

public class MultiplexNotFoundException extends RuntimeException {
    public MultiplexNotFoundException(String message) {
        super(message);
    }
}