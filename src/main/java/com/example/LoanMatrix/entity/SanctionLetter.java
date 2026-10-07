package com.example.LoanMatrix.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sanction_letters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SanctionLetter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SanctionLetterId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DealId", nullable = false)
    private LoanDeal loanDeal;

    @Column(name = "LoanAmount", precision = 18, scale = 2)
    private BigDecimal loanAmount;

    @Column(name = "InterestRate", precision = 5, scale = 2)
    private BigDecimal interestRate;

    @Column(name = "TenureMonths")
    private Integer tenureMonths;

    @Column(name = "EmiAmount", precision = 18, scale = 2)
    private BigDecimal emiAmount;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;
}