package com.shandilya.movie.model;

import com.shandilya.movie.constants.PaymentStatus;
import com.shandilya.movie.dto.PaymentDTO;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue
    private Long id;
    private Long bookingId;
    private String paymentStatus;
    private Integer retries;

    public PaymentDTO transform() {
        return PaymentDTO.builder()
                .bookingId(bookingId)
                .paymentStatus(paymentStatus)
                .retries(retries)
                .build();
    }
}