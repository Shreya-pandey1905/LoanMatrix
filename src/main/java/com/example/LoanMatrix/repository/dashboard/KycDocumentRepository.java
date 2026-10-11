
package com.example.LoanMatrix.repository.dashboard;

import com.example.LoanMatrix.entity.Customer;
import com.example.LoanMatrix.entity.KycDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KycDocumentRepository
        extends JpaRepository<KycDocument, Long> {

    long countDistinctCustomerByVerificationStatus(
            String verificationStatus
    );

    List<KycDocument> findByCustomer(Customer customer);
}
