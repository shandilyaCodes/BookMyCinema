package com.shandilya.movie.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "multiplex")
public class Multiplex {
    @Id
    @GeneratedValue
    private Long id;
    private String multiplexName;

    public com.shandilya.movie.dto.MultiplexDTO transform() {
        return com.shandilya.movie.dto.MultiplexDTO.builder()
                .id(id)
                .multiplexName(multiplexName)
                .build();
    }
}