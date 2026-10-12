package com.example.LoanMatrix.dto.eligibilityDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEligibilityResponse {

    private Long customerId;
    private String customerName;
    private Integer cibilScore;
    private Boolean eligible;
    private BigDecimal eligibleLoanAmount;
    private String rejectionReason;
}
