package com.shandilya.movie.dto;

import com.shandilya.movie.model.Multiplex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultiplexDTO {
    private Long id;
    private String multiplexName;

    public Multiplex transform() {
        return Multiplex.builder()
                .multiplexName(multiplexName)
                .build();
    }
}