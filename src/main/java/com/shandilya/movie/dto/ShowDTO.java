package com.shandilya.movie.dto;

import com.shandilya.movie.model.Show;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowDTO {
    private Long movieId;
    private Long audiId;
    private Long multiplexId;
    private Date startTime;
    private Integer durationInSeconds;

    public Show transform() {
        return Show.builder()
                .movieId(movieId)
                .audiId(audiId)
                .multiplexId(multiplexId)
                .startTime(startTime)
                .durationInSeconds(durationInSeconds)
                .build();
    }
}