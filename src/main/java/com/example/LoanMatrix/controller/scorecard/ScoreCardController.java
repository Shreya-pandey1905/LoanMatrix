package com.example.LoanMatrix.controller.scorecard;


import com.example.LoanMatrix.dto.scorecardDtos.ScoreCardResponse;
import com.example.LoanMatrix.response.ApiResponse;
import com.example.LoanMatrix.service.scorecard.ScoreCardService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/officer/score-card")
public class ScoreCardController {

    private final ScoreCardService scoreCardService;

    public ScoreCardController(ScoreCardService scoreCardService) {
        this.scoreCardService = scoreCardService;
    }

    @GetMapping("/customers")
    public ResponseEntity<ApiResponse<Page<ScoreCardResponse>>>
    getEligibleCustomers(
            @RequestParam(defaultValue = "0") int page) {

        Page<ScoreCardResponse> response =
                scoreCardService.getEligibleCustomers(page);

        ApiResponse<Page<ScoreCardResponse>> apiResponse =
                ApiResponse.<Page<ScoreCardResponse>>builder()
                        .success(true)
                        .message("Eligible customers fetched successfully")
                        .data(response)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<ApiResponse<ScoreCardResponse>> getScoreCard(
            @PathVariable Long customerId) {

        ScoreCardResponse response =
                scoreCardService.getScoreCard(customerId);

        ApiResponse<ScoreCardResponse> apiResponse =
                ApiResponse.<ScoreCardResponse>builder()
                        .success(true)
                        .message("Score card fetched successfully")
                        .data(response)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }
}
