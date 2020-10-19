package com.shandilya.movie.service;

import com.shandilya.movie.exceptions.NotFoundException;
import com.shandilya.movie.model.Movie;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MovieService {
    private final Map<String, Movie> movieMap;

    public MovieService() {
        this.movieMap = new HashMap<>();
    }

    public Movie getMovie(String movieId) {
        if (!movieMap.containsKey(movieId)) {
            throw new NotFoundException("Movie Not Found!");
        }
        return movieMap.get(movieId);
    }

    public Movie createMovie(String movieName) {
        final String movieId = UUID.randomUUID().toString();
        Movie movie = new Movie(movieId, movieName);
        movieMap.put(movieId, movie);
        return movie;
    }
}