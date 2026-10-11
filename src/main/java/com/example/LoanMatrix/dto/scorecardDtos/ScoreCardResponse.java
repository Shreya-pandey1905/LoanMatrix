package com.example.LoanMatrix.dto.scorecardDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreCardResponse {

    private Long customerId;
    private String customerName;
    private String panCard;
    private Integer cibilScore;
    private String riskCategory;
    private BigDecimal eligibleLoanAmount;
    private Boolean eligible;
}