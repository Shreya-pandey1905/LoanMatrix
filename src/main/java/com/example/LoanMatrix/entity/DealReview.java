package com.example.LoanMatrix.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deal_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DealReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DealReviewId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DealId", nullable = false)
    private LoanDeal loanDeal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OfficerId", nullable = false)
    private User officer;

    @Column(name = "Status")
    private String status;
}
