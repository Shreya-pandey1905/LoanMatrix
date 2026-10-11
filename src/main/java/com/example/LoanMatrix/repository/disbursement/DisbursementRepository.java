package com.example.LoanMatrix.repository.disbursement;

import com.example.LoanMatrix.entity.Disbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisbursementRepository extends JpaRepository<Disbursement, Long> {

    Optional<Disbursement> findByLoanDeal_Id(Long dealId);

    boolean existsByLoanDeal_Id(Long dealId);

    List<Disbursement> findAllByOrderByDisbursementDateDesc();

    List<Disbursement> findByLoanDeal_Customer_Id(Long customerId);
}