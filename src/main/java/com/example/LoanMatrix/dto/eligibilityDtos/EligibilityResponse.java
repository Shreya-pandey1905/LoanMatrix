package com.example.LoanMatrix.dto.eligibilityDtos;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EligibilityResponse {

    private Long customerId;

    private String customerName;

    private String panCard;

    private Integer cibilScore;

    private String cibilRange;

    private BigDecimal eligibleLoanAmount;

    private Boolean eligible;

    private Boolean autoApproved;

    private Boolean officerActionRequired;
}