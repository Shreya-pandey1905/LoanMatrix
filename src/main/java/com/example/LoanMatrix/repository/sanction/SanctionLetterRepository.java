package com.example.LoanMatrix.repository.sanction;


import com.example.LoanMatrix.entity.SanctionLetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SanctionLetterRepository extends JpaRepository<SanctionLetter, Long> {

    Optional<SanctionLetter> findByLoanDeal_Id(Long dealId);

    boolean existsByLoanDeal_Id(Long dealId);

    List<SanctionLetter> findAllByOrderByCreatedAtDesc();

    List<SanctionLetter> findByLoanDeal_Customer_Id(Long customerId);


}