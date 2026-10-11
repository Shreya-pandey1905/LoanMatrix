package com.example.LoanMatrix.dto.cibilDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CibilResponse {

    private String customerName;

    private String panCard;

    private Integer cibilScore;

    private LocalDate checkDate;
}
