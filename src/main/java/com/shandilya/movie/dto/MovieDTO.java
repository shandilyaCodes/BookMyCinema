package com.shandilya.movie.dto;

import com.shandilya.movie.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private String movieName;
    private String genre;

    public Movie transform() {
        return Movie.builder()
                .movieName(movieName)
                .genre(genre)
                .build();
    }
}