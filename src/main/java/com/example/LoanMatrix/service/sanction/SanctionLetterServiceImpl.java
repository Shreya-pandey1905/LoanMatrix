package com.example.LoanMatrix.service.sanction;

import com.example.LoanMatrix.dto.sanction.GenerateSanctionRequestDto;
import com.example.LoanMatrix.dto.sanction.SanctionLetterDetailDto;
import com.example.LoanMatrix.dto.sanction.SanctionLetterResponseDto;
import com.example.LoanMatrix.entity.Customer;
import com.example.LoanMatrix.entity.DealReview;
import com.example.LoanMatrix.entity.LoanDeal;
import com.example.LoanMatrix.entity.SanctionLetter;
import com.example.LoanMatrix.exception.InvalidLoanOperationException;
import com.example.LoanMatrix.exception.ResourceNotFoundException;
import com.example.LoanMatrix.repository.deal.DealReviewRepository;
import com.example.LoanMatrix.repository.sanction.LoanDealSanctionRepository;
import com.example.LoanMatrix.repository.sanction.SanctionLetterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SanctionLetterServiceImpl implements SanctionLetterService {

    private final SanctionLetterRepository sanctionLetterRepository;
    private final LoanDealSanctionRepository loanDealRepository;
    private final DealReviewRepository dealReviewRepository;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    @Transactional
    public SanctionLetterResponseDto generateSanctionLetter(GenerateSanctionRequestDto request) {
        log.info("Request received to generate sanction letter for Deal ID: {}", request.getDealId());

        LoanDeal loanDeal = loanDealRepository.findById(request.getDealId())
                .orElseThrow(() -> new ResourceNotFoundException("Loan deal not found with ID: " + request.getDealId()));

        Optional<DealReview> latestReview = dealReviewRepository.findTopByLoanDeal_IdOrderByIdDesc(request.getDealId());

        if (latestReview.isEmpty()) {
            throw new InvalidLoanOperationException("Cannot generate sanction letter. Deal ID " + request.getDealId() + " is still PENDING review.");
        }

        if (!"APPROVED".equalsIgnoreCase(latestReview.get().getStatus())) {
            throw new InvalidLoanOperationException("Cannot generate sanction letter. Deal ID " + request.getDealId() + " has status: " + latestReview.get().getStatus());
        }

        if (sanctionLetterRepository.existsByLoanDeal_Id(request.getDealId())) {
            throw new InvalidLoanOperationException("Sanction letter is already generated for this deal.");
        }

        BigDecimal finalAmount = request.getLoanAmount() != null
                ? request.getLoanAmount()
                : (loanDeal.getApprovedAmount() != null ? loanDeal.getApprovedAmount() : loanDeal.getLoanAmount());
        BigDecimal finalRate = request.getInterestRate() != null ? request.getInterestRate() : loanDeal.getInterestRate();
        Integer finalTenure = request.getTenureMonths() != null ? request.getTenureMonths() : loanDeal.getTenureMonths();
        BigDecimal finalEmi = request.getEmiAmount() != null ? request.getEmiAmount() : loanDeal.getEmiAmount();

        SanctionLetter sanctionLetter = new SanctionLetter();
        sanctionLetter.setLoanDeal(loanDeal);
        sanctionLetter.setLoanAmount(finalAmount);
        sanctionLetter.setInterestRate(finalRate);
        sanctionLetter.setTenureMonths(finalTenure);
        sanctionLetter.setEmiAmount(finalEmi);
        sanctionLetter.setCreatedAt(LocalDateTime.now());

        SanctionLetter saved = sanctionLetterRepository.save(sanctionLetter);
        return mapToResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SanctionLetterDetailDto getSanctionLetterDocument(Long dealId) {
        log.info("Fetching complete document payload for Deal ID: {}", dealId);

        SanctionLetter letter = sanctionLetterRepository.findByLoanDeal_Id(dealId)
                .orElseThrow(() -> new ResourceNotFoundException("Sanction letter not found for Deal ID: " + dealId));

        LoanDeal deal = letter.getLoanDeal();
        Customer customer = deal.getCustomer();

        String applicantName = "Abhishek Mishra";
        String mobile = "7800209058";
        String address = "Mumbai, Maharashtra";

        if (customer != null) {
            String first = customer.getFirstName() != null ? customer.getFirstName() : "";
            String last = customer.getLastName() != null ? customer.getLastName() : "";
            String full = (first + " " + last).trim();
            if (!full.isEmpty()) applicantName = full;
            if (customer.getMobileNo() != null && !customer.getMobileNo().isBlank()) mobile = customer.getMobileNo();

        }

        LocalDate sanctionDate = letter.getCreatedAt() != null ? letter.getCreatedAt().toLocalDate() : LocalDate.now();

        return SanctionLetterDetailDto.builder()
                .headerTitle("LOAN SANCTION LETTER")
                .letterDate(sanctionDate)
                .applicantName(applicantName)
                .applicationNo(String.format("APP%03d", deal.getId()))
                .sanctionDate(sanctionDate.format(DATE_FORMATTER))
                .mobileNo(mobile)
                .loanType(formatLoanType(deal.getLoanType()))
                .loanAmountSanctioned(letter.getLoanAmount())
                .referenceInterestRate(letter.getInterestRate() + "% per annum")
                .loanTenure(letter.getTenureMonths())
                .totalProcessingCharges("Up to 0.5% of Loan Amount")
                .originationFee(new BigDecimal("2500"))
                .sanctionLetterValidity(180)
                .amountOfEmi(letter.getEmiAmount())
                .propertyAddress(address)
                .bankName("ABC Bank")
                .authorizedSignatory("ABC Bank")
                .additionalConditions(List.of(
                        "1. Repayment from ABC Bank",
                        "2. Legal vetting and search to be conducted",
                        "3. NOC and offered collateral",
                        "4. Confirmation form, official ID and copy of ID"
                ))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SanctionLetterResponseDto> getAllSanctionLetters() {
        return sanctionLetterRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SanctionLetterResponseDto getSanctionLetterById(Long id) {
        SanctionLetter letter = sanctionLetterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sanction letter not found with ID: " + id));
        return mapToResponseDto(letter);
    }

    @Override
    @Transactional(readOnly = true)
    public SanctionLetterResponseDto getSanctionLetterByDealId(Long dealId) {
        SanctionLetter letter = sanctionLetterRepository.findByLoanDeal_Id(dealId)
                .orElseThrow(() -> new ResourceNotFoundException("Sanction letter not found for Deal ID: " + dealId));
        return mapToResponseDto(letter);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SanctionLetterResponseDto> getSanctionLettersByCustomerId(Long customerId) {
        return sanctionLetterRepository.findByLoanDeal_Customer_Id(customerId)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    private String formatLoanType(String loanType) {
        if (loanType == null) return "Loan";
        String normalized = loanType.replace("_", " ").toLowerCase();
        if (normalized.contains("home")) return "Home Loan";
        if (normalized.contains("car")) return "Car Loan";
        return loanType;
    }

    private SanctionLetterResponseDto mapToResponseDto(SanctionLetter letter) {
        LoanDeal deal = letter.getLoanDeal();
        Customer customer = deal != null ? deal.getCustomer() : null;

        String customerFullName = "N/A";
        String customerMobile = "N/A";

        if (customer != null) {
            String first = customer.getFirstName() != null ? customer.getFirstName() : "";
            String last = customer.getLastName() != null ? customer.getLastName() : "";
            customerFullName = (first + " " + last).trim();
            customerMobile = customer.getMobileNo() != null ? customer.getMobileNo() : "N/A";
        }

        return SanctionLetterResponseDto.builder()
                .sanctionLetterId(letter.getId())
                .dealId(deal != null ? deal.getId() : null)
                .customerId(customer != null ? customer.getId() : null)
                .customerName(customerFullName)
                .mobileNumber(customerMobile)
                .loanType(deal != null ? formatLoanType(deal.getLoanType()) : "N/A")
                .loanAmount(letter.getLoanAmount())
                .interestRate(letter.getInterestRate())
                .tenureMonths(letter.getTenureMonths())
                .emiAmount(letter.getEmiAmount())
                .createdAt(letter.getCreatedAt())
                .disbursementStatus("APPROVED")
                .build();
    }
}