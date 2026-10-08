package com.example.LoanMatrix.service.EmiScheduler;

import com.example.LoanMatrix.entity.EmiSchedule;
import com.example.LoanMatrix.entity.LoanAccount;

import com.example.LoanMatrix.repository.EmiScheduleRepository.EmiScheduleRepository;
import com.example.LoanMatrix.repository.EmiScheduleRepository.LoanAccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmiScheduleServiceImpl implements EmiSchedulerService {

    private final EmiScheduleRepository emiScheduleRepository;
    private final LoanAccountRepository loanAccountRepository;

    public EmiScheduleServiceImpl(EmiScheduleRepository emiScheduleRepository,
                                  LoanAccountRepository loanAccountRepository) {
        this.emiScheduleRepository = emiScheduleRepository;
        this.loanAccountRepository = loanAccountRepository;
    }


    @Override
    public List<EmiSchedule> generateSchedule(Long loanAccountId) {

        LoanAccount loanAccount = loanAccountRepository.findById(loanAccountId)
                .orElseThrow(() -> new RuntimeException("Loan account not found"));

        if (emiScheduleRepository.existsByLoanAccountId(loanAccountId)) {
            throw new RuntimeException("EMI schedule already exists for this loan account");
        }

        BigDecimal outstandingPrincipal = loanAccount.getOutstandingPrincipal();
        BigDecimal emi = loanAccount.getEmiAmount();
        BigDecimal annualInterestRate = loanAccount.getInterestRate();

        int tenure = loanAccount.getTenureMonths();

        BigDecimal monthlyInterestRate = annualInterestRate
                .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);

        Integer emiDay = loanAccount.getLoanDeal().getEmiDay();

        LocalDate firstDueDate = loanAccount.getDisbursementDate()
                .withDayOfMonth(emiDay);

        if (!firstDueDate.isAfter(loanAccount.getDisbursementDate())) {
            firstDueDate = firstDueDate.plusMonths(1);
        }

        List<EmiSchedule> schedules = new ArrayList<>();

        for (int i = 1; i <= tenure; i++) {

            LocalDate dueDate = firstDueDate.plusMonths(i - 1);

            BigDecimal openingBalance = outstandingPrincipal;

            BigDecimal interestAmount = openingBalance
                    .multiply(monthlyInterestRate)
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal principalAmount = emi.subtract(interestAmount);

            if (principalAmount.compareTo(openingBalance) > 0) {
                principalAmount = openingBalance;
            }

            BigDecimal actualEmi = principalAmount.add(interestAmount);

            BigDecimal closingBalance = openingBalance.subtract(principalAmount)
                    .setScale(2, RoundingMode.HALF_UP);

            EmiSchedule schedule = new EmiSchedule();

            schedule.setLoanAccount(loanAccount);
            schedule.setInstallmentNo(i);
            schedule.setDueDate(dueDate);
            schedule.setOpeningBalance(openingBalance);
            schedule.setInterestAmount(interestAmount);
            schedule.setPrincipalAmount(principalAmount);
            schedule.setEmi(actualEmi);
            schedule.setClosingBalance(closingBalance);
            schedule.setPaymentStatus("PENDING");

            schedules.add(schedule);

            outstandingPrincipal = closingBalance;

            if (outstandingPrincipal.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
        }

        return emiScheduleRepository.saveAll(schedules);
    }

    @Override
    public List<EmiSchedule> getSchedule(Long loanAccountId) {

        return emiScheduleRepository
                .findByLoanAccountIdOrderByInstallmentNoAsc(loanAccountId);
    }

    @Override
    public void recalculateSchedule(Long loanAccountId) {

        LoanAccount loanAccount = loanAccountRepository.findById(loanAccountId)
                .orElseThrow(() -> new RuntimeException("Loan account not found"));

        List<EmiSchedule> schedules =
                emiScheduleRepository.findByLoanAccountIdOrderByInstallmentNoAsc(
                        loanAccountId
                );

        BigDecimal outstandingPrincipal = loanAccount.getOutstandingPrincipal();
        BigDecimal emi = loanAccount.getEmiAmount();
        BigDecimal annualInterestRate = loanAccount.getInterestRate();

        BigDecimal monthlyInterestRate = annualInterestRate
                .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);

        for (EmiSchedule schedule : schedules) {

            if ("PAID".equals(schedule.getPaymentStatus())) {
                outstandingPrincipal = schedule.getClosingBalance();
                continue;
            }

            BigDecimal openingBalance = outstandingPrincipal;

            BigDecimal interestAmount = openingBalance
                    .multiply(monthlyInterestRate)
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal principalAmount = emi.subtract(interestAmount);

            if (principalAmount.compareTo(openingBalance) > 0) {
                principalAmount = openingBalance;
            }

            BigDecimal actualEmi = principalAmount.add(interestAmount);

            BigDecimal closingBalance = openingBalance
                    .subtract(principalAmount)
                    .setScale(2, RoundingMode.HALF_UP);

            schedule.setOpeningBalance(openingBalance);
            schedule.setInterestAmount(interestAmount);
            schedule.setPrincipalAmount(principalAmount);
            schedule.setEmi(actualEmi);
            schedule.setClosingBalance(closingBalance);

            outstandingPrincipal = closingBalance;
        }

        emiScheduleRepository.saveAll(schedules);
    }
}