package com.example.LoanMatrix.dto.deal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanTypeOptionDto {
    private String typeKey;        // "HOME_LOAN", "CAR_LOAN"
    private String label;          // "Home Loan", "Car Loan"
    private BigDecimal interestRate; // 8.50, 10.50
}