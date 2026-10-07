package com.example.LoanMatrix.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "eligibility_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EligibilityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EligibilityResultId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId", nullable = false)
    private Customer customer;

    @Column(name = "CibilScore")
    private Integer cibilScore;

    @Column(name = "IsEligible")
    private Boolean eligible;

    @Column(name = "EligibleLoanAmount", precision = 18, scale = 2)
    private BigDecimal eligibleLoanAmount;

    @Column(name = "RejectionReason")
    private String rejectionReason;
}
