package com.example.LoanMatrix.service.eligibility;


import com.example.LoanMatrix.dto.eligibilityDtos.CustomerEligibilityResponse;
import com.example.LoanMatrix.dto.eligibilityDtos.EligibilityResponse;
import com.example.LoanMatrix.dto.eligibilityDtos.PendingCustomerResponse;

import java.util.List;

public interface EligibilityService {

    List<PendingCustomerResponse> getPendingCustomers();
    EligibilityResponse checkEligibility(Long customerId);

    void approveEligibility(Long customerId);

    void rejectEligibility(Long customerId);
    CustomerEligibilityResponse getCustomerEligibility(Long customerId);
}
