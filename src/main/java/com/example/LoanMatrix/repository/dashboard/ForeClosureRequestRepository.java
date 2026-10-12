package com.example.LoanMatrix.repository.dashboard;

import com.example.LoanMatrix.entity.ForeClosureRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForeClosureRequestRepository
        extends JpaRepository<ForeClosureRequest, Long> {

    long countByStatus(String status);
}