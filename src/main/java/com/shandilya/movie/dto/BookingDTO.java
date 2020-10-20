package com.shandilya.movie.dto;

import com.shandilya.movie.constants.BookingStatus;
import com.shandilya.movie.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Long showId;
    private String seats;
    private Long userId;
    private String bookingStatus;

    public Booking transform() {
        return Booking.builder()
                .showId(showId)
                .seats(seats)
                .userId(userId)
                .bookingStatus(BookingStatus.CREATED.toString())
                .build();
    }
}