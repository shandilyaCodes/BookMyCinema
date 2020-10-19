package com.shandilya.movie.controller;

import com.shandilya.movie.model.Seat;
import com.shandilya.movie.model.Show;
import com.shandilya.movie.service.BookingService;
import com.shandilya.movie.service.ShowService;
import com.shandilya.movie.service.TheaterService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BookingController {
    private final ShowService showService;
    private final BookingService bookingService;
    private final TheaterService theaterService;

    public String createBooking(String userId, String showId, List<String> seats) {
        final Show show = showService.getShow(showId);
        final List<Seat> seatsInTheater = seats.stream().map(theaterService::getSeat).collect(Collectors.toList());
        return bookingService.createBooking(userId, show, seatsInTheater).getBookingId();
    }
}
