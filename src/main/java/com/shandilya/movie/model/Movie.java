package com.shandilya.movie.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Movie {
    private final String movieId;
    private final String movieName;
}