package com.example.LoanMatrix.controller.eligibility;

import com.example.LoanMatrix.dto.eligibilityDtos.CustomerEligibilityResponse;
import com.example.LoanMatrix.response.ApiResponse;
import com.example.LoanMatrix.service.eligibility.EligibilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/eligibility")
public class UserEligibilityController {

    private final EligibilityService eligibilityService;

    public UserEligibilityController(
            EligibilityService eligibilityService) {
        this.eligibilityService = eligibilityService;
    }

    @GetMapping("/result")
    public ResponseEntity<ApiResponse<CustomerEligibilityResponse>>
    getCustomerEligibility(@RequestParam Long customerId) {

        CustomerEligibilityResponse response =
                eligibilityService.getCustomerEligibility(customerId);

        ApiResponse<CustomerEligibilityResponse> apiResponse =
                ApiResponse.<CustomerEligibilityResponse>builder()
                        .success(true)
                        .message("Eligibility result fetched successfully")
                        .data(response)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }
}
