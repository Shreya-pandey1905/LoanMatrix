
package com.example.LoanMatrix.dto.customerDashboardDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDashboardResponse {

    private Long totalActiveLoanAccounts;

    private List<CustomerLoanAccountResponse> loanAccounts;

    private BigDecimal totalLoanAmount;

    private BigDecimal totalOutstandingPrincipal;

    private BigDecimal totalAmountPaid;

    private Long emisPaid;

    private Long emisPending;

    private BigDecimal upcomingEmiAmount;

    private LocalDate upcomingEmiDueDate;

    private List<CustomerPaymentResponse> recentPayments;

    private List<CustomerDocumentResponse> documents;
}
