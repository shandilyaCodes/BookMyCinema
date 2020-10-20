package com.shandilya.movie.dto;

import com.shandilya.movie.model.Audi;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AudiDTO {
    private String audiName;
    private Integer seatRows;
    private Integer seatColumns;

    public Audi transform() {
        return Audi.builder()
                .audiName(audiName)
                .seatRows(seatRows)
                .seatColumns(seatColumns)
                .build();
    }
}