package com.example.LoanMatrix.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "loan_deals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanDeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DealId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId", nullable = false)
    private Customer customer;

    @Column(name = "LoanType")
    private String loanType;

    @Column(name = "LoanAmount", precision = 18, scale = 2)
    private BigDecimal loanAmount;

    @Column(name = "InterestRate", precision = 5, scale = 2)
    private BigDecimal interestRate;

    @Column(name = "TenureMonths")
    private Integer tenureMonths;

    @Column(name = "EmiAmount", precision = 18, scale = 2)
    private BigDecimal emiAmount;

    @Column(name = "BankName")
    private String bankName;

    @Column(name = "BankAccountNumber")
    private String bankAccountNumber;

    @Column(name = "IFSCCode")
    private String ifscCode;

    @Column(name = "EmiDay")
    private Integer emiDay;

    @Column(name = "ApprovedAmount", precision = 18, scale = 2)
    private BigDecimal approvedAmount;
}