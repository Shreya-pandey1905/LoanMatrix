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
public class FullClosureResponseDto {
    private Long requestId;
    private Long loanAccountId;
    private String loanAccountNo;
    private String customerName;
    private String foreClosureType;
    private BigDecimal outstandingPrincipal;
    private BigDecimal penaltyAmount;
    private BigDecimal totalSettlementAmount;
    private String status;
    private Boolean isPaid;
    private LocalDate requestedDate;
    private LocalDate approvedDate;
    private String reason;
}