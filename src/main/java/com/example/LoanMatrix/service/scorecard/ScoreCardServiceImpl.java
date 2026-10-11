package com.example.LoanMatrix.service.scorecard;

import com.example.LoanMatrix.dto.scorecardDtos.ScoreCardResponse;
import com.example.LoanMatrix.entity.EligibilityResult;
import com.example.LoanMatrix.repository.eligibility.EligibilityResultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ScoreCardServiceImpl implements ScoreCardService {

    private final EligibilityResultRepository eligibilityResultRepository;

    public ScoreCardServiceImpl(
            EligibilityResultRepository eligibilityResultRepository) {
        this.eligibilityResultRepository = eligibilityResultRepository;
    }

    @Override
    public Page<ScoreCardResponse> getEligibleCustomers(int page) {

        Page<EligibilityResult> results =
                eligibilityResultRepository.findByEligibleTrue(
                        PageRequest.of(page, 5)
                );

        return results.map(result -> {

            ScoreCardResponse response =
                    new ScoreCardResponse();

            response.setCustomerId(
                    result.getCustomer().getId()
            );

            response.setCustomerName(
                    result.getCustomer().getFirstName()
                            + " "
                            + result.getCustomer().getLastName()
            );

            response.setPanCard(
                    result.getCustomer().getPanCard()
            );

            response.setCibilScore(
                    result.getCibilScore()
            );

            response.setEligibleLoanAmount(
                    result.getEligibleLoanAmount()
            );

            response.setEligible(
                    result.getEligible()
            );

            // Risk category
            int score = result.getCibilScore();

            String riskCategory;

            if (score >= 900) {
                riskCategory = "Excellent";
            } else if (score >= 800) {
                riskCategory = "Very Good";
            } else if (score >= 750) {
                riskCategory = "Good";
            } else if (score >= 700) {
                riskCategory = "Average";
            } else if (score >= 650) {
                riskCategory = "Risky";
            } else {
                riskCategory = "Reject";
            }

            response.setRiskCategory(riskCategory);

            return response;
        });
    }

    @Override
    public ScoreCardResponse getScoreCard(Long customerId) {

        EligibilityResult result =
                eligibilityResultRepository.findById(customerId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Eligibility result not found"
                                )
                        );

        if (!Boolean.TRUE.equals(result.getEligible())) {
            throw new RuntimeException(
                    "Customer is not eligible for score card"
            );
        }

        ScoreCardResponse response =
                new ScoreCardResponse();

        response.setCustomerId(
                result.getCustomer().getId()
        );

        response.setCustomerName(
                result.getCustomer().getFirstName()
                        + " "
                        + result.getCustomer().getLastName()
        );

        response.setPanCard(
                result.getCustomer().getPanCard()
        );

        response.setCibilScore(
                result.getCibilScore()
        );

        response.setEligibleLoanAmount(
                result.getEligibleLoanAmount()
        );

        response.setEligible(
                result.getEligible()
        );

        int score = result.getCibilScore();

        String riskCategory;

        if (score >= 900) {
            riskCategory = "Excellent";
        } else if (score >= 800) {
            riskCategory = "Very Good";
        } else if (score >= 750) {
            riskCategory = "Good";
        } else if (score >= 700) {
            riskCategory = "Average";
        } else if (score >= 650) {
            riskCategory = "Risky";
        } else {
            riskCategory = "Reject";
        }

        response.setRiskCategory(riskCategory);

        return response;
    }
}