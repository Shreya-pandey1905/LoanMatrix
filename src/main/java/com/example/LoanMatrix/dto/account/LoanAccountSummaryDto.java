package com.example.LoanMatrix.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanAccountSummaryDto {
    private Long id;
    private String loanAccountNo;
    private String customerName;
    private BigDecimal loanAmount;
    private BigDecimal outstandingPrincipal;
    private String status;
    private BigDecimal interestRate;
    private Integer tenureMonths;
    private BigDecimal emiAmount;
    private LocalDate disbursementDate;
    private BigDecimal totalPaid;
}