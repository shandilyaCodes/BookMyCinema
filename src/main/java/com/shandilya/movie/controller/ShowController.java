package com.shandilya.movie.controller;

import com.shandilya.movie.model.Movie;
import com.shandilya.movie.model.Screen;
import com.shandilya.movie.model.Seat;
import com.shandilya.movie.model.Show;
import com.shandilya.movie.service.MovieService;
import com.shandilya.movie.service.SeatAvailabilityService;
import com.shandilya.movie.service.ShowService;
import com.shandilya.movie.service.TheaterService;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ShowController {

    private final SeatAvailabilityService availabilityService;
    private final ShowService showService;
    private final TheaterService theaterService;
    private final MovieService movieService;

    public String createShow(String movieId, String screenId, Date startTime, Integer movieDuration) {
        final Screen screen = theaterService.getScreen(screenId);
        final Movie movie = movieService.getMovie(movieId);
        final Show show = showService.createShow(movie, screen, startTime, movieDuration);
        return show.getShowId();
    }

    public List<String> getAvailableSeats(String showId) {
        final Show show = showService.getShow(showId);
        final List<Seat> availableSeats = availabilityService.getAvailableSeats(show);
        return availableSeats.stream().map(Seat::getSeatId).collect(Collectors.toList());
    }
}