package com.example.LoanMatrix.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "interest_accruals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InterestAccrual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InterestAccrualId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LoanAccountId", nullable = false)
    private LoanAccount loanAccount;

    @Column(name = "InterestAmount", precision = 18, scale = 2)
    private BigDecimal interestAmount;

    @Column(name = "AccrualDate")
    private LocalDate accrualDate;

    @Column(name = "InstallmentNo")
    private Integer installmentNo;

    @Column(name = "Status")
    private String status;
}
