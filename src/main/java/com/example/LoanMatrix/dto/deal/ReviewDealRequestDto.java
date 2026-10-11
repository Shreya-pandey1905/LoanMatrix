package com.example.LoanMatrix.dto.deal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDealRequestDto {
    private Long officerId;
    private String status; // "APPROVED" or "REJECTED"
}