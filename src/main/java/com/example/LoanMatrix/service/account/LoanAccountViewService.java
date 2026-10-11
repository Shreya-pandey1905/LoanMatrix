package com.example.LoanMatrix.service.account;

import com.example.LoanMatrix.dto.account.LoanAccountProfileDto;
import com.example.LoanMatrix.dto.account.LoanAccountSummaryDto;

import java.util.List;

public interface LoanAccountViewService {
    // For Loan Officer Table View
    List<LoanAccountSummaryDto> getAllAccountsForOfficer(String statusFilter);

    // For Customer Account Selection / List View
    List<LoanAccountSummaryDto> getAccountsForCustomer(Long customerId);

    // For the Detailed Card View (Both Officer & Customer)
    LoanAccountProfileDto getAccountDetailsByAccountNo(String loanAccountNo);
}