package com.shandilya.movie.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Show {
    @Id
    @GeneratedValue
    private Long id;
    private Long audiId;
    private Long movieId;
    private Date startTime;
    private Long multiplexId;
    private Integer durationInSeconds;
}