package com.example.LoanMatrix.repository.Support;

import com.example.LoanMatrix.entity.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {

    List<SupportTicket> findByCustomer_IdOrderByCreatedAtDesc(Long customerId);

    Optional<SupportTicket> findByIdAndCustomer_Id(Long ticketId, Long customerId);
}
