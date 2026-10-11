package com.example.LoanMatrix.controller.account;


import com.example.LoanMatrix.dto.account.LoanAccountProfileDto;
import com.example.LoanMatrix.dto.account.LoanAccountSummaryDto;
import com.example.LoanMatrix.response.ApiResponse;
import com.example.LoanMatrix.service.account.LoanAccountViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans/accounts")
@RequiredArgsConstructor
public class LoanAccountViewController {

    private final LoanAccountViewService loanAccountViewService;

    // 1. Officer Dashboard Table (Supports "All Status", "Active", "Closed" filters)
    @GetMapping("/officer/all")
    public ResponseEntity<ApiResponse<List<LoanAccountSummaryDto>>> getOfficerAccountTable(
            @RequestParam(required = false) String status) {
        List<LoanAccountSummaryDto> data = loanAccountViewService.getAllAccountsForOfficer(status);
        return ResponseEntity.ok(
                ApiResponse.<List<LoanAccountSummaryDto>>builder()
                        .success(true)
                        .message("Loan accounts loaded for officer table view.")
                        .data(data)
                        .build()
        );
    }

    // 2. Customer Portal: Shows all loans associated with the logged-in customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<LoanAccountSummaryDto>>> getCustomerAccounts(
            @PathVariable Long customerId) {
        List<LoanAccountSummaryDto> data = loanAccountViewService.getAccountsForCustomer(customerId);
        return ResponseEntity.ok(
                ApiResponse.<List<LoanAccountSummaryDto>>builder()
                        .success(true)
                        .message("Customer loans retrieved.")
                        .data(data)
                        .build()
        );
    }

    // 3. Card View Details: Fetches the vertical details card by Loan Account Number
    @GetMapping("/details/{loanAccountNo}")
    public ResponseEntity<ApiResponse<LoanAccountProfileDto>> getAccountDetailsCard(
            @PathVariable String loanAccountNo) {
        LoanAccountProfileDto data = loanAccountViewService.getAccountDetailsByAccountNo(loanAccountNo);
        return ResponseEntity.ok(
                ApiResponse.<LoanAccountProfileDto>builder()
                        .success(true)
                        .message("Loan account card details loaded.")
                        .data(data)
                        .build()
        );
    }
}