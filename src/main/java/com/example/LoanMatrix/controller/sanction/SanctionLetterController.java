package com.example.LoanMatrix.controller.sanction;

import com.example.LoanMatrix.dto.sanction.GenerateSanctionRequestDto;
import com.example.LoanMatrix.dto.sanction.SanctionLetterDetailDto;
import com.example.LoanMatrix.dto.sanction.SanctionLetterResponseDto;
import com.example.LoanMatrix.response.ApiResponse;
import com.example.LoanMatrix.service.sanction.SanctionLetterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sanction-letters")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class SanctionLetterController {

    private final SanctionLetterService sanctionLetterService;

    // 1. Generate sanction letter record
    @PostMapping("/generate")
    public ResponseEntity<ApiResponse<SanctionLetterResponseDto>> generateSanctionLetter(
            @RequestBody GenerateSanctionRequestDto request) {
        log.info("REST request to generate sanction letter for Deal ID: {}", request.getDealId());
        SanctionLetterResponseDto responseDto = sanctionLetterService.generateSanctionLetter(request);

        return new ResponseEntity<>(
                ApiResponse.<SanctionLetterResponseDto>builder()
                        .success(true)
                        .message("Sanction letter generated successfully")
                        .data(responseDto)
                        .error(null)
                        .build(),
                HttpStatus.CREATED
        );
    }

    // 2. Fetch the formal document payload (matches letter layout)
    @GetMapping("/document/deal/{dealId}")
    public ResponseEntity<ApiResponse<SanctionLetterDetailDto>> getSanctionLetterDocument(
            @PathVariable Long dealId) {
        log.info("REST request to fetch sanction letter document for Deal ID: {}", dealId);
        SanctionLetterDetailDto document = sanctionLetterService.getSanctionLetterDocument(dealId);

        return ResponseEntity.ok(
                ApiResponse.<SanctionLetterDetailDto>builder()
                        .success(true)
                        .message("Sanction letter document retrieved successfully")
                        .data(document)
                        .error(null)
                        .build()
        );
    }

    // 3. Fetch summary by Deal ID
    @GetMapping("/deal/{dealId}")
    public ResponseEntity<ApiResponse<SanctionLetterResponseDto>> getSanctionLetterByDealId(
            @PathVariable Long dealId) {
        log.info("REST request to fetch sanction letter for Deal ID: {}", dealId);
        SanctionLetterResponseDto letter = sanctionLetterService.getSanctionLetterByDealId(dealId);

        return ResponseEntity.ok(
                ApiResponse.<SanctionLetterResponseDto>builder()
                        .success(true)
                        .message("Fetched sanction letter by Deal ID successfully")
                        .data(letter)
                        .error(null)
                        .build()
        );
    }

    // 4. Customer Portal: View all letters for a specific customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<SanctionLetterResponseDto>>> getLettersByCustomerId(
            @PathVariable Long customerId) {
        log.info("REST request to fetch sanction letters for Customer ID: {}", customerId);
        List<SanctionLetterResponseDto> letters = sanctionLetterService.getSanctionLettersByCustomerId(customerId);

        return ResponseEntity.ok(
                ApiResponse.<List<SanctionLetterResponseDto>>builder()
                        .success(true)
                        .message("Fetched customer sanction letters successfully")
                        .data(letters)
                        .error(null)
                        .build()
        );
    }

    // 5. Officer Dashboard: Table of all sanction letters
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<SanctionLetterResponseDto>>> getAllSanctionLetters() {
        log.info("REST request to fetch all sanction letters");
        List<SanctionLetterResponseDto> letters = sanctionLetterService.getAllSanctionLetters();

        return ResponseEntity.ok(
                ApiResponse.<List<SanctionLetterResponseDto>>builder()
                        .success(true)
                        .message("Fetched all sanction letters successfully")
                        .data(letters)
                        .error(null)
                        .build()
        );
    }

    // 6. Fetch by primary key ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SanctionLetterResponseDto>> getSanctionLetterById(
            @PathVariable Long id) {
        log.info("REST request to fetch sanction letter by ID: {}", id);
        SanctionLetterResponseDto letter = sanctionLetterService.getSanctionLetterById(id);

        return ResponseEntity.ok(
                ApiResponse.<SanctionLetterResponseDto>builder()
                        .success(true)
                        .message("Fetched sanction letter details successfully")
                        .data(letter)
                        .error(null)
                        .build()
        );
    }
}