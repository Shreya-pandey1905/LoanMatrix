package com.example.LoanMatrix.repository.Payment;

import com.example.LoanMatrix.entity.PenaltyCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenaltyChargeRepository extends JpaRepository<PenaltyCharge, Long> {

    List<PenaltyCharge> findByEmiSchedule_Id(long emiScheduleId);

}
