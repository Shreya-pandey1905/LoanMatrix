package com.example.LoanMatrix.controller.eligibility;

import com.example.LoanMatrix.dto.eligibilityDtos.CustomerEligibilityResponse;
import com.example.LoanMatrix.dto.eligibilityDtos.EligibilityResponse;
import com.example.LoanMatrix.dto.eligibilityDtos.PendingCustomerResponse;
import com.example.LoanMatrix.response.ApiResponse;
import com.example.LoanMatrix.service.eligibility.EligibilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/officer/eligibility")
public class EligibilityController {

    private final EligibilityService eligibilityService;

    public EligibilityController(EligibilityService eligibilityService) {
        this.eligibilityService = eligibilityService;
    }

    @GetMapping("/pending-customers")
    public ResponseEntity<ApiResponse<List<PendingCustomerResponse>>> getPendingCustomers() {

        List<PendingCustomerResponse> customers =
                eligibilityService.getPendingCustomers();

        ApiResponse<List<PendingCustomerResponse>> response =
                ApiResponse.<List<PendingCustomerResponse>>builder()
                        .success(true)
                        .message("Pending customers fetched successfully")
                        .data(customers)
                        .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/check")
    public ResponseEntity<ApiResponse<EligibilityResponse>> checkEligibility(
            @RequestParam Long customerId) {

        EligibilityResponse response =
                eligibilityService.checkEligibility(customerId);

        ApiResponse<EligibilityResponse> apiResponse =
                ApiResponse.<EligibilityResponse>builder()
                        .success(true)
                        .message("Eligibility checked successfully")
                        .data(response)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/approve")
    public ResponseEntity<ApiResponse<Void>> approveEligibility(
            @RequestParam Long customerId) {

        eligibilityService.approveEligibility(customerId);

        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Eligibility approved successfully")
                        .data(null)
                        .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/reject")
    public ResponseEntity<ApiResponse<Void>> rejectEligibility(
            @RequestParam Long customerId) {

        eligibilityService.rejectEligibility(customerId);

        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Eligibility rejected successfully")
                        .data(null)
                        .build();

        return ResponseEntity.ok(response);
    }

//    @GetMapping("/customer/result")
//    public ResponseEntity<ApiResponse<CustomerEligibilityResponse>>
//    getCustomerEligibility(@RequestParam Long customerId) {
//
//        CustomerEligibilityResponse response =
//                eligibilityService.getCustomerEligibility(customerId);
//
//        ApiResponse<CustomerEligibilityResponse> apiResponse =
//                ApiResponse.<CustomerEligibilityResponse>builder()
//                        .success(true)
//                        .message("Eligibility result fetched successfully")
//                        .data(response)
//                        .build();
//
//        return ResponseEntity.ok(apiResponse);
//    }
}
