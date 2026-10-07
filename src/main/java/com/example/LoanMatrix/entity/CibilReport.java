package com.example.LoanMatrix.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "cibil_reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CibilReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CibilReportId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId", nullable = false)
    private Customer customer;

    @Column(name = "PanNo")
    private String panNo;

    @Column(name = "CibilScore")
    private Integer cibilScore;

    @Column(name = "CheckDate")
    private LocalDate checkDate;
}
