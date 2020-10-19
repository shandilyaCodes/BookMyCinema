package com.shandilya.movie.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class Show {
    private final String showId;
    private final Movie movie;
    private final Screen screen;
    private final Date startTime;
    private final Integer durationInSeconds;
}