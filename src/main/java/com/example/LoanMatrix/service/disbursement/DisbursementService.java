package com.example.LoanMatrix.service.disbursement;

import com.example.LoanMatrix.dto.disbursement.DisbursementRequestDto;
import com.example.LoanMatrix.dto.disbursement.DisbursementResponseDto;

import java.util.List;

public interface DisbursementService {

    DisbursementResponseDto processDisbursement(DisbursementRequestDto requestDto);

    List<DisbursementResponseDto> getAllDisbursements();

    DisbursementResponseDto getDisbursementByDealId(Long dealId);

    DisbursementResponseDto getDisbursementById(Long id);

    List<DisbursementResponseDto> getDisbursementsByCustomerId(Long customerId);
}