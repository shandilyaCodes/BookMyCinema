package com.shandilya.movie.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Seat {
    private final String seatId;
    private final int rowNo;
    private final int seatNo;
}