package com.example.LoanMatrix.dto.Payment;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentOrderResponse {

    private String razorpayOrderId;

    private BigDecimal amount;

    private String currency;

    private String razorpayKeyId;

}
