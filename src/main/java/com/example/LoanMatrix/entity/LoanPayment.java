package com.example.LoanMatrix.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "loan_payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LoanPaymentId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LoanAccountId", nullable = false)
    private LoanAccount loanAccount;

    @Column(name = "PaymentAmount", precision = 18, scale = 2)
    private BigDecimal paymentAmount;

    @Column(name = "PaymentDate")
    private LocalDate paymentDate;

    @Column(name = "PaymentStatus")
    private String paymentStatus;

    @Column(name = "PaymentName")
    private String paymentName;
}
