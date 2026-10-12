
package com.example.LoanMatrix.repository.dashboard;

import com.example.LoanMatrix.entity.Customer;
import com.example.LoanMatrix.entity.EmiSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmiScheduleRepository
        extends JpaRepository<EmiSchedule, Long> {

    long countByPaymentStatus(String paymentStatus);

    List<EmiSchedule> findByLoanAccount_Customer(Customer customer);
}
