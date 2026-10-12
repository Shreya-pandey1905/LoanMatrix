package com.example.LoanMatrix.dto.eligibilityDtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendingCustomerResponse {

    private Long customerId;
    private String customerName;
}