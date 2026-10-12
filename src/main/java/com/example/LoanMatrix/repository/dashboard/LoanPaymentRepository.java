
package com.example.LoanMatrix.repository.dashboard;

import com.example.LoanMatrix.entity.Customer;
import com.example.LoanMatrix.entity.LoanPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanPaymentRepository
        extends JpaRepository<LoanPayment, Long> {

    List<LoanPayment>
    findByLoanAccount_CustomerOrderByPaymentDateDesc(Customer customer);
}
