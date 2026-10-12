package com.example.LoanMatrix.repository.closure;

import com.example.LoanMatrix.entity.LoanClosure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanClosureRepository extends JpaRepository<LoanClosure, Long> {
    Optional<LoanClosure> findByLoanAccount_Id(Long loanAccountId);
}