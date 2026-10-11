package com.example.LoanMatrix.dto.deal;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplyLoanResponseDto {

    private Long dealId;
    private Long customerId;
    private String customerName;
    private String loanType;
    private BigDecimal loanAmount;
    private BigDecimal approvedAmount;
    private BigDecimal interestRate;
    private Integer tenureMonths;
    private BigDecimal emiAmount;
    private String bankName;
    private String bankAccountNumber;
    private String ifscCode;
    private Integer emiDay;
    private String status;
    private LocalDate appliedDate;
    private String rejectionReason;
}