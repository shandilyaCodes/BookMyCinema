package com.shandilya.movie.model;

import com.shandilya.movie.dto.AudiDTO;
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
@Table(name = "audi")
public class Audi {
    @Id
    @Getter
    @GeneratedValue
    private Long id;
    private String audiName;
    private Integer seatRows;
    private Integer seatColumns;
}