package com.example.LoanMatrix.controller.officerDashboard;

import com.example.LoanMatrix.dto.officerDashboardDtos.OfficerDashboardResponse;
import com.example.LoanMatrix.response.ApiResponse;
import com.example.LoanMatrix.service.officerDashboard.OfficerDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/officer/dashboard")
public class OfficerDashboardController {

    private final OfficerDashboardService officerDashboardService;

    public OfficerDashboardController(
            OfficerDashboardService officerDashboardService) {

        this.officerDashboardService = officerDashboardService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<OfficerDashboardResponse>> getDashboard() {

        OfficerDashboardResponse dashboard =
                officerDashboardService.getDashboard();

        ApiResponse<OfficerDashboardResponse> response =
                ApiResponse.<OfficerDashboardResponse>builder()
                        .success(true)
                        .message("Officer dashboard fetched successfully")
                        .data(dashboard)
                        .build();

        return ResponseEntity.ok(response);
    }
}