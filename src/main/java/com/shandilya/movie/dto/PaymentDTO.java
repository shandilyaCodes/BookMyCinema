package com.shandilya.movie.dto;

import com.shandilya.movie.constants.PaymentStatus;
import com.shandilya.movie.model.Payment;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private Long bookingId;
    private String paymentStatus;
    private Integer retries;

    public Payment transform() {
        return Payment.builder()
                .bookingId(bookingId)
                .retries(0)
                .build();
    }
}