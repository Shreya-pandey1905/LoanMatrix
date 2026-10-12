package com.example.LoanMatrix.dto.Support;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketStatusRequest {

    @NotBlank(message = "Status is required")
    private String status;
}