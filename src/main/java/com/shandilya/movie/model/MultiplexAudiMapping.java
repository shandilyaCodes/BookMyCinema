package com.shandilya.movie.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultiplexAudiMapping {
    @Id
    @GeneratedValue
    private Long id;
    private Long multiplexId;
    private Long audiId;
}