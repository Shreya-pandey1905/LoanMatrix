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
public class DisbursementRequestDto {
    private Long dealId;
    private BigDecimal disbursementAmount;
    private LocalDate disbursementDate;
    private String bankPartner;
}