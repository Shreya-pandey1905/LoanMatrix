package com.example.LoanMatrix.dto.officerDashboardDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficerDashboardResponse {

    private Long totalCustomers;

    private Long loanCustomers;

    private Long approvedKyc;

    private BigDecimal totalLoanAmount;

    private Long activeLoans;

    private BigDecimal todaysCollection;

    private Long pendingEmi;

    private Long foreclosureRequests;
}
