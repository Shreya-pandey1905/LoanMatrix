package com.example.LoanMatrix.service.closure;

import com.example.LoanMatrix.dto.closure.PartialClosureRequestDto;
import com.example.LoanMatrix.dto.closure.PartialClosureResponseDto;

import java.util.List;

public interface PartialClosureService {
    PartialClosureResponseDto submitPartialRequest(PartialClosureRequestDto requestDto);
    PartialClosureResponseDto approvePartialRequest(Long requestId, Long officerId);
    PartialClosureResponseDto processPartialPayment(Long requestId);
    List<PartialClosureResponseDto> getPendingRequests();
    List<PartialClosureResponseDto> getRequestsByLoanAccountId(Long loanAccountId);
}