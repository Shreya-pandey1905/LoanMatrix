package com.example.LoanMatrix.service.EmiScheduler;

public interface PenaltyChargeService {

    void checkAndCreatePenalty(Long loanAccountId);
}