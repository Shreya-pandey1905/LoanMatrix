package com.example.LoanMatrix.repository.account;

import com.example.LoanMatrix.entity.LoanAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanAccountQueryRepository extends JpaRepository<LoanAccount, Long> {
    Optional<LoanAccount> findByLoanAccountNo(String loanAccountNo);
    List<LoanAccount> findByCustomer_IdOrderByCreatedAtDesc(Long customerId);
    List<LoanAccount> findByLoanStatusOrderByCreatedAtDesc(String loanStatus);
    List<LoanAccount> findAllByOrderByCreatedAtDesc();
}