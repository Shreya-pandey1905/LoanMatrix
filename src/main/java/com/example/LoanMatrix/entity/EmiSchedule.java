package com.example.LoanMatrix.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "emi_schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmiSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmiScheduleId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LoanAccountId", nullable = false)
    private LoanAccount loanAccount;

    @Column(name = "InstallmentNo")
    private Integer installmentNo;

    @Column(name = "DueDate")
    private LocalDate dueDate;

    @Column(name = "PrincipalAmount", precision = 18, scale = 2)
    private BigDecimal principalAmount;

    @Column(name = "InterestAmount", precision = 18, scale = 2)
    private BigDecimal interestAmount;

    @Column(name = "OpeningBalance", precision = 18, scale = 2)
    private BigDecimal openingBalance;

    @Column(name = "ClosingBalance", precision = 18, scale = 2)
    private BigDecimal closingBalance;

    @Column(name = "Emi", precision = 18, scale = 2)
    private BigDecimal emi;

    @Column(name = "PaymentStatus")
    private String paymentStatus;

    @Column(name = "PaidDate")
    private LocalDate paidDate;

    @Column(name = "CancellationReason")
    private String cancellationReason;
}
