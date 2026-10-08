package com.example.LoanMatrix.service.EmiScheduler;
import com.example.LoanMatrix.entity.EmiSchedule;
import com.example.LoanMatrix.entity.LoanAccount;
import com.example.LoanMatrix.entity.PenaltyCharge;
import com.example.LoanMatrix.repository.EmiScheduleRepository.EmiScheduleRepository;

import com.example.LoanMatrix.repository.EmiScheduleRepository.LoanAccountRepository;
import com.example.LoanMatrix.repository.EmiScheduleRepository.PenaltyChargeRepository;
import com.example.LoanMatrix.service.Email.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PenaltyChargeServiceImpl implements PenaltyChargeService {

    private final EmiScheduleRepository emiScheduleRepository;
    private final LoanAccountRepository loanAccountRepository;
    private final PenaltyChargeRepository penaltyChargeRepository;
    private final EmailService emailService;

    @Value("${loan.emi.missed-penalty}")
    private BigDecimal penaltyAmount;

    public PenaltyChargeServiceImpl(
            EmiScheduleRepository emiScheduleRepository,
            LoanAccountRepository loanAccountRepository,
            PenaltyChargeRepository penaltyChargeRepository,
            EmailService emailService) {

        this.emiScheduleRepository = emiScheduleRepository;
        this.loanAccountRepository = loanAccountRepository;
        this.penaltyChargeRepository = penaltyChargeRepository;
        this.emailService = emailService;
    }

    @Override
    public void checkAndCreatePenalty(Long loanAccountId) {

        LoanAccount loanAccount = loanAccountRepository.findById(loanAccountId)
                .orElseThrow(() -> new RuntimeException("Loan account not found"));

        List<EmiSchedule> schedules =
                emiScheduleRepository.findByLoanAccountIdOrderByInstallmentNoAsc(
                        loanAccountId
                );

        int missedEmiCount = 0;

        for (EmiSchedule schedule : schedules) {

            if ("PENDING".equals(schedule.getPaymentStatus())
                    && schedule.getDueDate().isBefore(LocalDate.now())) {

                missedEmiCount++;

                boolean penaltyExists =
                        penaltyChargeRepository.existsByEmiScheduleId(schedule.getId());

                if (!penaltyExists) {

                    PenaltyCharge penalty = new PenaltyCharge();

                    penalty.setEmiSchedule(schedule);
                    penalty.setLoanAccount(loanAccount);
                    penalty.setPenaltyAmount(penaltyAmount);
                    penalty.setReason("Missed EMI");
                    penalty.setStatus("PENDING");
                    penalty.setCreatedAt(LocalDateTime.now());

                    penaltyChargeRepository.save(penalty);
                }
            }
        }

        if (missedEmiCount >= 3
                && !"BLOCKED".equals(loanAccount.getLoanStatus())) {

            blockLoanAccount(loanAccount);
        }
    }

    private void blockLoanAccount(LoanAccount loanAccount) {

        loanAccount.setLoanStatus("BLOCKED");

        String loanType = loanAccount.getLoanDeal()
                .getLoanType();

        if ("HOME".equalsIgnoreCase(loanType)) {
            loanAccount.setInterestRate(BigDecimal.valueOf(15));
        } else if ("VEHICLE".equalsIgnoreCase(loanType)) {
            loanAccount.setInterestRate(BigDecimal.valueOf(10));
        } else if ("PERSONAL".equalsIgnoreCase(loanType)) {
            loanAccount.setInterestRate(BigDecimal.valueOf(10));
        }

        loanAccountRepository.save(loanAccount);

        String email = loanAccount.getCustomer().getEmail();

        String subject = "Loan Account Blocked";

        String message =
                "Your loan account " + loanAccount.getLoanAccountNo()
                        + " has been blocked because 3 or more EMIs were missed. "
                        + "Please contact the loan office for further assistance.";

        emailService.sendKycStatusEmail(
                email,
                subject,
                message
        );
    }
}