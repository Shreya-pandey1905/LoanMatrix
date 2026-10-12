
package com.example.LoanMatrix.repository.dashboard;

import com.example.LoanMatrix.entity.Customer;
import com.example.LoanMatrix.entity.LoanAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanAccountRepository
        extends JpaRepository<LoanAccount, Long> {

    long countDistinctCustomerBy();

    long countByLoanStatus(String loanStatus);

    List<LoanAccount> findByCustomerAndLoanStatus(
            Customer customer,
            String loanStatus
    );

    List<LoanAccount> findByCustomer(Customer customer);
}
