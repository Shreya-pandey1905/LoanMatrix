package com.example.LoanMatrix.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "loan_closures")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanClosure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LoanClosureId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LoanAccountId", nullable = false)
    private LoanAccount loanAccount;

    @Column(name = "ClosureType")
    private String closureType;

    @Column(name = "FinalSettlementAmount", precision = 18, scale = 2)
    private BigDecimal finalSettlementAmount;

    @Column(name = "ClosureDate")
    private LocalDate closureDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClosedBy")
    private User closedBy;

    @Column(name = "Remarks")
    private String remarks;

    @Column(name = "ClosureStatus")
    private String closureStatus;
}
