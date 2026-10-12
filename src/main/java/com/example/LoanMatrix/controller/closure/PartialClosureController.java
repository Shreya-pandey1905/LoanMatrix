package com.example.LoanMatrix.controller.closure;

import com.example.LoanMatrix.dto.closure.PartialClosureRequestDto;
import com.example.LoanMatrix.dto.closure.PartialClosureResponseDto;
import com.example.LoanMatrix.response.ApiResponse;
import com.example.LoanMatrix.service.closure.PartialClosureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans/closure/partial")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class PartialClosureController {

    private final PartialClosureService partialClosureService;

    // 1. Customer submits a partial closure request
    @PostMapping("/request")
    public ResponseEntity<ApiResponse<PartialClosureResponseDto>> submitRequest(
            @Valid @RequestBody PartialClosureRequestDto requestDto) {
        log.info("REST request to submit partial closure for account: {}", requestDto.getLoanAccountId());
        PartialClosureResponseDto response = partialClosureService.submitPartialRequest(requestDto);
        return new ResponseEntity<>(
                ApiResponse.<PartialClosureResponseDto>builder()
                        .success(true)
                        .message("Partial foreclosure request submitted successfully.")
                        .data(response)
                        .build(),
                HttpStatus.CREATED
        );
    }

    // 2. Officer reviews & approves the request
    @PutMapping("/approve/{requestId}")
    public ResponseEntity<ApiResponse<PartialClosureResponseDto>> approveRequest(
            @PathVariable Long requestId,
            @RequestParam Long officerId) {
        log.info("REST request to approve closure request ID: {} by officer: {}", requestId, officerId);
        PartialClosureResponseDto response = partialClosureService.approvePartialRequest(requestId, officerId);
        return ResponseEntity.ok(
                ApiResponse.<PartialClosureResponseDto>builder()
                        .success(true)
                        .message("Partial foreclosure request approved.")
                        .data(response)
                        .build()
        );
    }

    // 3. Customer executes partial prepayment (automatic balance reduction)
    @PostMapping("/pay/{requestId}")
    public ResponseEntity<ApiResponse<PartialClosureResponseDto>> processPayment(
            @PathVariable Long requestId) {
        log.info("REST request to process partial payment for request ID: {}", requestId);
        PartialClosureResponseDto response = partialClosureService.processPartialPayment(requestId);
        return ResponseEntity.ok(
                ApiResponse.<PartialClosureResponseDto>builder()
                        .success(true)
                        .message("Partial prepayment processed. Outstanding balance updated.")
                        .data(response)
                        .build()
        );
    }

    // 4. Officer view: List all pending requests
    @GetMapping("/pending")
    public ResponseEntity<ApiResponse<List<PartialClosureResponseDto>>> getPendingRequests() {
        return ResponseEntity.ok(
                ApiResponse.<List<PartialClosureResponseDto>>builder()
                        .success(true)
                        .message("Pending closure requests retrieved successfully.")
                        .data(partialClosureService.getPendingRequests())
                        .build()
        );
    }

    // 5. Customer / Account view: List all requests for this loan account
    @GetMapping("/account/{loanAccountId}")
    public ResponseEntity<ApiResponse<List<PartialClosureResponseDto>>> getRequestsByLoanAccountId(
            @PathVariable Long loanAccountId) {
        return ResponseEntity.ok(
                ApiResponse.<List<PartialClosureResponseDto>>builder()
                        .success(true)
                        .message("Closure requests fetched for loan account.")
                        .data(partialClosureService.getRequestsByLoanAccountId(loanAccountId))
                        .build()
        );
    }
}