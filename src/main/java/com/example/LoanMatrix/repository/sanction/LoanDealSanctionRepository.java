package com.example.LoanMatrix.repository.sanction;

import com.example.LoanMatrix.entity.LoanDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanDealSanctionRepository extends JpaRepository<LoanDeal, Long> {
    List<LoanDeal> findByCustomer_Id(Long customerId);

}