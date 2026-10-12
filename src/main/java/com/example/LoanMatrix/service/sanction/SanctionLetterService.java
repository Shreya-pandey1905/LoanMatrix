package com.example.LoanMatrix.service.sanction;

import com.example.LoanMatrix.dto.sanction.GenerateSanctionRequestDto;
import com.example.LoanMatrix.dto.sanction.SanctionLetterDetailDto;
import com.example.LoanMatrix.dto.sanction.SanctionLetterResponseDto;

import java.util.List;

public interface SanctionLetterService {
    SanctionLetterResponseDto generateSanctionLetter(GenerateSanctionRequestDto request);
    SanctionLetterDetailDto getSanctionLetterDocument(Long dealId);
    List<SanctionLetterResponseDto> getAllSanctionLetters();
    SanctionLetterResponseDto getSanctionLetterById(Long id);
    SanctionLetterResponseDto getSanctionLetterByDealId(Long dealId);
    List<SanctionLetterResponseDto> getSanctionLettersByCustomerId(Long customerId);
}