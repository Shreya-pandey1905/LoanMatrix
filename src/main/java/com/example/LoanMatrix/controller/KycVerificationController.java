package com.example.LoanMatrix.controller;

import com.example.LoanMatrix.entity.KycDocument;

import com.example.LoanMatrix.response.ApiResponse;
import com.example.LoanMatrix.service.KycVerification.KycVerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping("/kyc")
public class KycVerificationController {

    private final KycVerificationService kycVerificationService;

    public KycVerificationController(KycVerificationService kycVerificationService) {
        this.kycVerificationService = kycVerificationService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<KycDocument>> uploadDocument(
            @RequestParam Long customerId,
            @RequestParam String documentType,
            @RequestParam MultipartFile filePath) {

        KycDocument document = kycVerificationService.uploadDocument(
                customerId, documentType, filePath);

        ApiResponse<KycDocument> response = ApiResponse.<KycDocument>builder()
                .success(true)
                .message("KYC document uploaded successfully")
                .data(document)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<KycDocument>>> getCustomerDocuments(
            @PathVariable Long customerId) {

        List<KycDocument> documents =
                kycVerificationService.getCustomerDocuments(customerId);

        ApiResponse<List<KycDocument>> response =
                ApiResponse.<List<KycDocument>>builder()
                        .success(true)
                        .message("KYC documents fetched successfully")
                        .data(documents)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/pending")
    public ResponseEntity<ApiResponse<List<KycDocument>>> getPendingDocuments() {

        List<KycDocument> documents =
                kycVerificationService.getPendingDocuments();

        ApiResponse<List<KycDocument>> response =
                ApiResponse.<List<KycDocument>>builder()
                        .success(true)
                        .message("Pending KYC documents fetched successfully")
                        .data(documents)
                        .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{documentId}/approve")
    public ResponseEntity<ApiResponse<KycDocument>> approveDocument(
            @PathVariable Long documentId) {

        KycDocument document =
                kycVerificationService.approveDocument(documentId);

        ApiResponse<KycDocument> response =
                ApiResponse.<KycDocument>builder()
                        .success(true)
                        .message("KYC document approved successfully")
                        .data(document)
                        .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{documentId}/reject")
    public ResponseEntity<ApiResponse<KycDocument>> rejectDocument(
            @PathVariable Long documentId) {

        KycDocument document =
                kycVerificationService.rejectDocument(documentId);

        ApiResponse<KycDocument> response =
                ApiResponse.<KycDocument>builder()
                        .success(true)
                        .message("KYC document rejected successfully")
                        .data(document)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/document/{documentId}")
    public ResponseEntity<byte[]> viewDocument(
            @PathVariable Long documentId) {

        byte[] file = kycVerificationService.viewDocument(documentId);

        return ResponseEntity.ok(file);
    }
}