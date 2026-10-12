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
public class CreateSupportTicketRequest {

    @NotBlank(message = "Subject is required")
    private String subject;

    @NotBlank(message = "Description is required")
    private String description;

    private Long loanAccountId;
}