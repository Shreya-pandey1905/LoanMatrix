package com.example.LoanMatrix.controller.Payment;

import com.example.LoanMatrix.dto.Payment.CreatePaymentRequest;
import com.example.LoanMatrix.dto.Payment.PaymentHistoryResponse;
import com.example.LoanMatrix.dto.Payment.PaymentOrderResponse;
import com.example.LoanMatrix.dto.Payment.VerifyPaymentRequest;
import com.example.LoanMatrix.entity.LoanPayment;
import com.example.LoanMatrix.response.ApiResponse;
import com.example.LoanMatrix.service.PaymentService.PaymentService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create-order")
    public ResponseEntity<ApiResponse<PaymentOrderResponse>> createOrder(@RequestBody CreatePaymentRequest request) throws RazorpayException {

        PaymentOrderResponse response = paymentService.createPaymentOrder(request);

        ApiResponse<PaymentOrderResponse> apiResponse = ApiResponse.<PaymentOrderResponse>builder()
                .success(true)
                .message("Payment order created Successfully")
                .data(response)
                .error(null)
                .build();

        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping("/verify-payment")
    public ResponseEntity<ApiResponse<Void>> verifyPayment(@RequestBody VerifyPaymentRequest request) throws RazorpayException {

        paymentService.verifyPayment(request);

        ApiResponse<Void> apiResponse =
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Payment verified successfully")
                        .data(null)
                        .error(null)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/history/{loanAccountId}")
    public ResponseEntity<ApiResponse<List<PaymentHistoryResponse>>> getPaymentHistory(
            @PathVariable Long loanAccountId) {

        List<PaymentHistoryResponse> payments =
                paymentService.getPaymentHistory(loanAccountId);

        ApiResponse<List<PaymentHistoryResponse>> apiResponse =
                ApiResponse.<List<PaymentHistoryResponse>>builder()
                        .success(true)
                        .message("Payment history fetched successfully")
                        .data(payments)
                        .error(null)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }

}
