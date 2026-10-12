package com.example.LoanMatrix.service.closure;

import com.example.LoanMatrix.dto.closure.FullClosureRequestDto;
import com.example.LoanMatrix.dto.closure.FullClosureResponseDto;

import java.util.List;

public interface FullClosureService {
    FullClosureResponseDto submitFullRequest(FullClosureRequestDto requestDto);
    FullClosureResponseDto approveFullRequest(Long requestId, Long officerId);
    FullClosureResponseDto processFullPayment(Long requestId);
    List<FullClosureResponseDto> getPendingRequests();
    List<FullClosureResponseDto> getPayableRequestsForCustomer(Long loanAccountId);
}