package com.example.LoanMatrix.exception;

import com.example.LoanMatrix.response.ApiResponse;
import com.example.LoanMatrix.response.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomerNotFound(
            CustomerNotFoundException ex) {

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .message("Customer not found")
                .data(null)
                .error(
                        ErrorDetails.builder()
                                .error("CUSTOMER_NOT_FOUND")
                                .details(ex.getMessage())
                                .build()
                )
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(CibilAlreadyGeneratedException.class)
    public ResponseEntity<ApiResponse<Void>> handleCibilAlreadyGenerated(
            CibilAlreadyGeneratedException ex) {

        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .success(false)
                        .message("CIBIL already generated")
                        .data(null)
                        .error(
                                ErrorDetails.builder()
                                        .error("CIBIL_ALREADY_GENERATED")
                                        .details(ex.getMessage())
                                        .build()
                        )
                        .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }


    @ExceptionHandler(EligibilityDecisionAlreadyMadeException.class)
    public ResponseEntity<ApiResponse<Void>> handleEligibilityDecisionAlreadyMade(
            EligibilityDecisionAlreadyMadeException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(
                        false,
                        ex.getMessage(),
                        null,
                        null
                ));
    }

}
