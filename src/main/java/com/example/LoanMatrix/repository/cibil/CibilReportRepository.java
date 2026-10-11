package com.example.LoanMatrix.repository.cibil;


import com.example.LoanMatrix.entity.CibilReport;
import com.example.LoanMatrix.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CibilReportRepository extends JpaRepository<CibilReport, Long> {
    boolean existsByCustomer(Customer customer);
    CibilReport findByCustomer(Customer customer);
}
