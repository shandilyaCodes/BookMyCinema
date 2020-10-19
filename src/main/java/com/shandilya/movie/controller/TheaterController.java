package com.shandilya.movie.controller;

import com.shandilya.movie.model.Screen;
import com.shandilya.movie.model.Theater;
import com.shandilya.movie.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/theater")
public class TheaterController {

    private final TheaterService theaterService;

    @PostMapping("/create")
    public String createTheater(String theaterName) {
        return theaterService.createTheater(theaterName).getTheaterId();
    }

    public String createScreenInTheater(String screenName, String theaterId) {
        final Theater theater = theaterService.getTheater(theaterId);
        return theaterService.createScreenInTheater(screenName, theater).getScreenId();
    }

    public String createSeatInScreen(Integer rowNumber, Integer seatNumber, String screenId) {
        final Screen screen = theaterService.getScreen(screenId);
        return theaterService.createSeatInScreen(rowNumber, seatNumber, screen).getSeatId();
    }
}