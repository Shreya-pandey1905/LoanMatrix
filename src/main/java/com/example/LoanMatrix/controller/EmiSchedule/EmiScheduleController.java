package com.example.LoanMatrix.controller.EmiSchedule;


import com.example.LoanMatrix.entity.EmiSchedule;
import com.example.LoanMatrix.service.EmiScheduler.EmiSchedulerService;
import com.example.LoanMatrix.service.EmiScheduler.PenaltyChargeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/emi")
public class EmiScheduleController {

    private final EmiSchedulerService emiScheduleService;
    private final PenaltyChargeService penaltyChargeService;

    public EmiScheduleController(
            EmiSchedulerService emiScheduleService,
            PenaltyChargeService penaltyChargeService) {

        this.emiScheduleService = emiScheduleService;
        this.penaltyChargeService = penaltyChargeService;
    }
    @PostMapping("/schedule/{loanAccountId}")
    public ResponseEntity<List<EmiSchedule>> generateSchedule(
            @PathVariable Long loanAccountId) {

        return ResponseEntity.ok(
                emiScheduleService.generateSchedule(loanAccountId)
        );
    }

    @GetMapping("/schedule/{loanAccountId}")
    public ResponseEntity<List<EmiSchedule>> getSchedule(
            @PathVariable Long loanAccountId) {

        return ResponseEntity.ok(
                emiScheduleService.getSchedule(loanAccountId)
        );
    }

    @PostMapping("/recalculate/{loanAccountId}")
    public ResponseEntity<String> recalculateSchedule(
            @PathVariable Long loanAccountId) {

        emiScheduleService.recalculateSchedule(loanAccountId);

        return ResponseEntity.ok("EMI schedule recalculated successfully");
    }

    @PostMapping("/penalty/{loanAccountId}")
    public ResponseEntity<String> createPenalty(
            @PathVariable Long loanAccountId) {

        penaltyChargeService.checkAndCreatePenalty(loanAccountId);

        return ResponseEntity.ok("Missed EMI penalty checked successfully");
    }
}