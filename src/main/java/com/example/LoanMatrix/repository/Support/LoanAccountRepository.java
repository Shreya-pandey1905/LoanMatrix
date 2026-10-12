package com.example.LoanMatrix.repository.Support;

import com.example.LoanMatrix.entity.LoanAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanAccountRepository extends JpaRepository<LoanAccount, Long> {

    Optional<LoanAccount> findByIdAndCustomer_Id(
            Long id,
            Long customerId
    );
}
