package com.example.LoanMatrix.dto.closure;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartialClosureRequestDto {

    @NotNull(message = "Loan Account ID is required")
    private Long loanAccountId;

    @NotNull(message = "Partial payment amount is required")
    @DecimalMin(value = "1.00", message = "Partial amount must be at least 1.00")
    private BigDecimal partialAmount;

    private LocalDate expectedClosureDate;

    private String reason;
}