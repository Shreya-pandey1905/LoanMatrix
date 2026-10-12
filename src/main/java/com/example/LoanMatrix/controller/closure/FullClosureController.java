package com.example.LoanMatrix.controller.closure;

import com.example.LoanMatrix.dto.closure.FullClosureRequestDto;
import com.example.LoanMatrix.dto.closure.FullClosureResponseDto;
import com.example.LoanMatrix.response.ApiResponse;
import com.example.LoanMatrix.service.closure.FullClosureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans/closure/full")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class FullClosureController {

    private final FullClosureService fullClosureService;

    @PostMapping("/request")
    public ResponseEntity<ApiResponse<FullClosureResponseDto>> submitRequest(
            @Valid @RequestBody FullClosureRequestDto requestDto) {
        log.info("REST request to submit full foreclosure for Account ID: {}", requestDto.getLoanAccountId());
        FullClosureResponseDto response = fullClosureService.submitFullRequest(requestDto);
        return new ResponseEntity<>(
                ApiResponse.<FullClosureResponseDto>builder()
                        .success(true)
                        .message("Full foreclosure request submitted successfully.")
                        .data(response)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/approve/{requestId}")
    public ResponseEntity<ApiResponse<FullClosureResponseDto>> approveRequest(
            @PathVariable Long requestId,
            @RequestParam Long officerId) {
        log.info("REST request to approve full foreclosure request: {} by officer: {}", requestId, officerId);
        FullClosureResponseDto response = fullClosureService.approveFullRequest(requestId, officerId);
        return ResponseEntity.ok(
                ApiResponse.<FullClosureResponseDto>builder()
                        .success(true)
                        .message("Full foreclosure request approved.")
                        .data(response)
                        .build()
        );
    }

    @PostMapping("/pay/{requestId}")
    public ResponseEntity<ApiResponse<FullClosureResponseDto>> processPayment(
            @PathVariable Long requestId) {
        log.info("REST request to process full foreclosure payment for Request ID: {}", requestId);
        FullClosureResponseDto response = fullClosureService.processFullPayment(requestId);
        return ResponseEntity.ok(
                ApiResponse.<FullClosureResponseDto>builder()
                        .success(true)
                        .message("Full foreclosure settled. Loan status marked as CLOSED.")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("/pending")
    public ResponseEntity<ApiResponse<List<FullClosureResponseDto>>> getPendingRequests() {
        return ResponseEntity.ok(
                ApiResponse.<List<FullClosureResponseDto>>builder()
                        .success(true)
                        .message("Pending full foreclosure requests retrieved.")
                        .data(fullClosureService.getPendingRequests())
                        .build()
        );
    }

    @GetMapping("/customer-payable/{loanAccountId}")
    public ResponseEntity<ApiResponse<List<FullClosureResponseDto>>> getPayableRequests(
            @PathVariable Long loanAccountId) {
        return ResponseEntity.ok(
                ApiResponse.<List<FullClosureResponseDto>>builder()
                        .success(true)
                        .message("Payable full foreclosure requests retrieved.")
                        .data(fullClosureService.getPayableRequestsForCustomer(loanAccountId))
                        .build()
        );
    }
}