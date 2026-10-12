package com.example.LoanMatrix.dto.sanction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SanctionLetterResponseDto {
    private Long sanctionLetterId;
    private Long dealId;
    private Long customerId;
    private String customerName;
    private String mobileNumber;
    private String loanType;
    private BigDecimal loanAmount;
    private BigDecimal interestRate;
    private Integer tenureMonths;
    private BigDecimal emiAmount;
    private LocalDateTime createdAt;
    private String disbursementStatus; // PENDING or DISBURSED
}