package com.example.LoanMatrix.dto.closure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullClosureRequestDto {
    private Long loanAccountId;
    private LocalDate expectedClosureDate;
    private String reason;
}