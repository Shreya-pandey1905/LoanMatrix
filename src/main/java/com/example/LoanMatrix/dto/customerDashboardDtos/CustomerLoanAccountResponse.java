
package com.example.LoanMatrix.dto.customerDashboardDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLoanAccountResponse {

    private Long loanAccountId;

    private String loanAccountNo;

    private BigDecimal loanAmount;

    private BigDecimal outstandingPrincipal;

    private BigDecimal interestRate;

    private Integer tenureMonths;

    private BigDecimal emiAmount;

    private BigDecimal totalPaidAmount;

    private String loanStatus;

    private LocalDate disbursementDate;
}
