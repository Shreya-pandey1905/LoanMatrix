package com.example.LoanMatrix.controller.disbursement;

import com.example.LoanMatrix.dto.disbursement.DisbursementRequestDto;
import com.example.LoanMatrix.dto.disbursement.DisbursementResponseDto;
import com.example.LoanMatrix.response.ApiResponse;
import com.example.LoanMatrix.service.disbursement.DisbursementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disbursements")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class DisbursementController {

    private final DisbursementService disbursementService;

    @PostMapping("/process")
    public ResponseEntity<ApiResponse<DisbursementResponseDto>> processDisbursement(
            @RequestBody DisbursementRequestDto requestDto) {
        log.info("REST request to process disbursement for Deal ID: {}", requestDto.getDealId());
        DisbursementResponseDto responseDto = disbursementService.processDisbursement(requestDto);

        ApiResponse<DisbursementResponseDto> response = ApiResponse.<DisbursementResponseDto>builder()
                .success(true)
                .message("Loan disbursed successfully and Loan Account created")
                .data(responseDto)
                .error(null)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<DisbursementResponseDto>>> getAllDisbursements() {
        log.info("REST request to fetch all disbursements");
        List<DisbursementResponseDto> list = disbursementService.getAllDisbursements();

        ApiResponse<List<DisbursementResponseDto>> response = ApiResponse.<List<DisbursementResponseDto>>builder()
                .success(true)
                .message("Fetched all disbursements successfully")
                .data(list)
                .error(null)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<DisbursementResponseDto>>> getDisbursementsByCustomerId(
            @PathVariable Long customerId) {
        log.info("REST request to fetch disbursements for Customer ID: {}", customerId);
        List<DisbursementResponseDto> list = disbursementService.getDisbursementsByCustomerId(customerId);

        ApiResponse<List<DisbursementResponseDto>> response = ApiResponse.<List<DisbursementResponseDto>>builder()
                .success(true)
                .message("Fetched customer active disbursements successfully")
                .data(list)
                .error(null)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/deal/{dealId}")
    public ResponseEntity<ApiResponse<DisbursementResponseDto>> getDisbursementByDealId(@PathVariable Long dealId) {
        log.info("REST request to fetch disbursement for Deal ID: {}", dealId);
        DisbursementResponseDto dto = disbursementService.getDisbursementByDealId(dealId);

        ApiResponse<DisbursementResponseDto> response = ApiResponse.<DisbursementResponseDto>builder()
                .success(true)
                .message("Fetched disbursement details for Deal ID successfully")
                .data(dto)
                .error(null)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DisbursementResponseDto>> getDisbursementById(@PathVariable Long id) {
        log.info("REST request to fetch disbursement by ID: {}", id);
        DisbursementResponseDto dto = disbursementService.getDisbursementById(id);

        ApiResponse<DisbursementResponseDto> response = ApiResponse.<DisbursementResponseDto>builder()
                .success(true)
                .message("Fetched disbursement details successfully")
                .data(dto)
                .error(null)
                .build();

        return ResponseEntity.ok(response);
    }
}
