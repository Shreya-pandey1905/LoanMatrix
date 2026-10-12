package com.example.LoanMatrix.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanAccountProfileDto {
    private Long id;
    private String loanAccountNo;
    private String customerName;
    private BigDecimal loanAmount;
    private BigDecimal outstandingPrincipal;
    private String loanStatus;
    private BigDecimal interestRate;
    private Integer tenureMonths;
    private BigDecimal emiAmount;
    private BigDecimal totalPaidAmount;
    private LocalDate disbursementDate;
    private LocalDateTime createdAt;
}