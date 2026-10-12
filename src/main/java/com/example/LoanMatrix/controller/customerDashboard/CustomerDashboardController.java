package com.example.LoanMatrix.controller.customerDashboard;

import com.example.LoanMatrix.dto.customerDashboardDtos.CustomerDashboardResponse;
import com.example.LoanMatrix.response.ApiResponse;
import com.example.LoanMatrix.service.customerDashboard.CustomerDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/dashboard")
public class CustomerDashboardController {

    private final CustomerDashboardService customerDashboardService;

    public CustomerDashboardController(
            CustomerDashboardService customerDashboardService) {

        this.customerDashboardService = customerDashboardService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CustomerDashboardResponse>>
    getDashboard(@RequestParam Long customerId) {

        CustomerDashboardResponse dashboard =
                customerDashboardService.getDashboard(customerId);

        ApiResponse<CustomerDashboardResponse> response =
                ApiResponse.<CustomerDashboardResponse>builder()
                        .success(true)
                        .message("Customer dashboard fetched successfully")
                        .data(dashboard)
                        .build();

        return ResponseEntity.ok(response);
    }
}
