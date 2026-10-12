package com.example.LoanMatrix.service.cibil;

import com.example.LoanMatrix.dto.cibilDtos.CibilResponse;

public interface CibilService
{
    CibilResponse getCibilDetails(Long customerId);
    CibilResponse generateCibilScore(Long customerId);
}
