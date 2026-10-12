package com.example.LoanMatrix.repository.eligibility;

import com.example.LoanMatrix.entity.Customer;
import com.example.LoanMatrix.entity.EligibilityResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EligibilityResultRepository
        extends JpaRepository<EligibilityResult, Long> {


    EligibilityResult findByCustomer(Customer customer);
    boolean existsByCustomer(Customer customer);
    Page<EligibilityResult> findByEligibleTrue(Pageable pageable);

}
