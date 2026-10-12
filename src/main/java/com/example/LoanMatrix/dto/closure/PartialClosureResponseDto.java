package com.example.LoanMatrix.dto.closure;

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
public class PartialClosureResponseDto {
    private Long requestId;
    private Long loanAccountId;
    private String loanAccountNo;
    private String customerName;
    private String foreClosureType;
    private BigDecimal partialAmount;
    private BigDecimal remainingOutstandingPrincipal;
    private BigDecimal totalPaidAmount;
    private String status;
    private Boolean isPaid;
    private LocalDate requestedDate;
    private LocalDate approvedDate;
    private LocalDate paidDate;
    private String reason;
}