package com.example.LoanMatrix.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LoanAccountId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DealId", nullable = false)
    private LoanDeal loanDeal;

    @Column(name = "LoanAccountNo", nullable = false, unique = true)
    private String loanAccountNo;

    @Column(name = "LoanAmount", precision = 18, scale = 2)
    private BigDecimal loanAmount;

    @Column(name = "OutstandingPrincipal", precision = 18, scale = 2)
    private BigDecimal outstandingPrincipal;

    @Column(name = "LoanStatus")
    private String loanStatus;

    @Column(name = "InterestRate", precision = 5, scale = 2)
    private BigDecimal interestRate;

    @Column(name = "TenureMonths")
    private Integer tenureMonths;

    @Column(name = "EmiAmount", precision = 18, scale = 2)
    private BigDecimal emiAmount;

    @Column(name = "DisbursementDate")
    private LocalDate disbursementDate;

    @Column(name = "TotalPaidAmount", precision = 18, scale = 2)
    private BigDecimal totalPaidAmount;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;
}