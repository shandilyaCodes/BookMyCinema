package com.shandilya.movie.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatMatrix {
    @Id
    @Getter
    @GeneratedValue
    private Long id;
    private Long showId;
    @Lob
    private String bookedSeats;
    @Lob
    private String availableSeats;
}