package com.shandilya.movie.controller;

import com.shandilya.movie.service.MovieService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    public String createMovie(String movieName) {
        return movieService.createMovie(movieName).getMovieId();
    }
}