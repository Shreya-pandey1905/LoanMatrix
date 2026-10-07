package com.example.LoanMatrix.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "fore_closure_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForeClosureRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ForeClosureRequestId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LoanAccountId", nullable = false)
    private LoanAccount loanAccount;

    @Column(name = "ForeClosureType")
    private String foreClosureType;

    @Column(name = "ForeClosureAmount", precision = 18, scale = 2)
    private BigDecimal foreClosureAmount;

    @Column(name = "PartialAmount", precision = 18, scale = 2)
    private BigDecimal partialAmount;

    @Column(name = "RequestedDate")
    private LocalDate requestedDate;

    @Column(name = "ExpectedClosureDate")
    private LocalDate expectedClosureDate;

    @Column(name = "Reason")
    private String reason;

    @Column(name = "Status")
    private String status;

    @Column(name = "ApprovedDate")
    private LocalDate approvedDate;

    @Column(name = "IsPaid")
    private Boolean paid;

    @Column(name = "PaidDate")
    private LocalDate paidDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClosedBy")
    private User closedBy;
}
