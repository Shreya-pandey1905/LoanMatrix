package com.example.LoanMatrix.dto.disbursement;

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
public class DisbursementResponseDto {
    private Long disbursementId;
    private Long dealId;
    private Long loanAccountId;
    private String loanAccountNo;
    private String customerName;
    private BigDecimal disburseAmount;
    private String bankPartner;
    private LocalDate disbursementDate;
    private String status;
}