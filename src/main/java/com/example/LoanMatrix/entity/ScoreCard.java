package com.example.LoanMatrix.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "score_cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ScoreCardId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId", nullable = false)
    private Customer customer;

    @Column(name = "CurrentStatus")
    private String currentStatus;

    @Column(name = "RejectionReason")
    private String rejectionReason;

    @Column(name = "AppliedDate")
    private LocalDate appliedDate;

    @Column(name = "CibilScore")
    private Integer cibilScore;

    @Column(name = "RiskCategory")
    private String riskCategory;

    @Column(name = "EligibleLoanAmount", precision = 18, scale = 2)
    private BigDecimal eligibleLoanAmount;
}
