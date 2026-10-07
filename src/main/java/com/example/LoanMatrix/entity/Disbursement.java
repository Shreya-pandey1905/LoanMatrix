package com.example.LoanMatrix.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "disbursements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Disbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DisbursementId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DealId", nullable = false)
    private LoanDeal loanDeal;

    @Column(name = "DisburseAmount", precision = 18, scale = 2)
    private BigDecimal disburseAmount;

    @Column(name = "BankPartner")
    private String bankPartner;

    @Column(name = "DisbursementDate")
    private LocalDate disbursementDate;

    @Column(name = "Status")
    private String status;
}
