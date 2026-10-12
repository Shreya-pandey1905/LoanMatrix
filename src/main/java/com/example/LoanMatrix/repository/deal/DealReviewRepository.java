package com.example.LoanMatrix.repository.deal;

import com.example.LoanMatrix.entity.DealReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DealReviewRepository extends JpaRepository<DealReview, Long> {
    boolean existsByLoanDeal_Id(Long dealId);
    Optional<DealReview> findTopByLoanDeal_IdOrderByIdDesc(Long dealId);
}