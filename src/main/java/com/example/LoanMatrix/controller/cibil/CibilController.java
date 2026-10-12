package com.example.LoanMatrix.controller.cibil;

import com.example.LoanMatrix.dto.cibilDtos.CibilResponse;
import com.example.LoanMatrix.response.ApiResponse;
import com.example.LoanMatrix.service.cibil.CibilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cibil")
public class CibilController {

    private final CibilService cibilService;

    public CibilController(CibilService cibilService) {
        this.cibilService = cibilService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CibilResponse>> getCibilDetails(
            @RequestParam Long customerId) {

        CibilResponse response =
                cibilService.getCibilDetails(customerId);

        ApiResponse<CibilResponse> apiResponse =
                ApiResponse.<CibilResponse>builder()
                        .success(true)
                        .message("CIBIL details fetched successfully")
                        .data(response)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/generate")
    public ResponseEntity<ApiResponse<CibilResponse>> generateCibilScore(
            @RequestParam Long customerId) {

        CibilResponse response =
                cibilService.generateCibilScore(customerId);

        ApiResponse<CibilResponse> apiResponse =
                ApiResponse.<CibilResponse>builder()
                        .success(true)
                        .message("CIBIL score generated successfully")
                        .data(response)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }
}