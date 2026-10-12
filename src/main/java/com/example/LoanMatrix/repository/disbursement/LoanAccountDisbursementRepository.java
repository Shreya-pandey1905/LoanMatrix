package com.example.LoanMatrix.repository.disbursement;

import com.example.LoanMatrix.entity.LoanAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanAccountDisbursementRepository extends JpaRepository<LoanAccount, Long> {


    // Add this query method here:
    Optional<LoanAccount> findByLoanDeal_Id(Long dealId);


    
}