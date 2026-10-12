package com.example.LoanMatrix.dto.deal;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplyLoanRequestDto {
    private Long customerId;
    private String loanType;
    private BigDecimal loanAmount;
    private Integer tenureMonths;
    private String bankName;
    private String bankAccountNumber;
    private String ifscCode;
    private Integer emiDay;
}