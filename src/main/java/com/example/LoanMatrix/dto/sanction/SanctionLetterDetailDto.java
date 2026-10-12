package com.example.LoanMatrix.dto.sanction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SanctionLetterDetailDto {
    private String headerTitle;              // "LOAN SANCTION LETTER"
    private LocalDate letterDate;            // e.g. 2026-10-07
    private String applicantName;            // e.g. "Abhishek Mishra"
    private String applicationNo;            // e.g. "APP008"
    private String sanctionDate;             // e.g. "07/10/2026"
    private String mobileNo;                 // e.g. "7800209058"
    private String loanType;                 // e.g. "Home Loan"
    private BigDecimal loanAmountSanctioned; // e.g. 300000.00
    private String referenceInterestRate;    // e.g. "8% per annum"
    private Integer loanTenure;              // e.g. 36
    private String totalProcessingCharges;   // "Up to 0.5% of Loan Amount"
    private BigDecimal originationFee;       // 2500.00
    private Integer sanctionLetterValidity;  // 180 (Days)
    private BigDecimal amountOfEmi;          // e.g. 9400.91
    private String propertyAddress;          // e.g. "Mumbai, Maharashtra"
    private List<String> additionalConditions;
    private String bankName;                 // "ABC Bank"
    private String authorizedSignatory;      // "ABC Bank"
}