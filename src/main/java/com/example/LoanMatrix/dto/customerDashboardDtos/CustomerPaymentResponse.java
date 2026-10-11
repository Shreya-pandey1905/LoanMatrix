
package com.example.LoanMatrix.dto.customerDashboardDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPaymentResponse {

    private Long paymentId;

    private String loanAccountNo;

    private String paymentName;

    private BigDecimal paymentAmount;

    private LocalDate paymentDate;

    private String paymentStatus;
}
