package com.example.LoanMatrix.service.customerDashboard;

import com.example.LoanMatrix.dto.customerDashboardDtos.CustomerDashboardResponse;

public interface CustomerDashboardService {

    CustomerDashboardResponse getDashboard(Long customerId);
}