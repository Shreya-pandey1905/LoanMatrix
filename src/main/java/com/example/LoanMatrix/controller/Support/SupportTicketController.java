package com.example.LoanMatrix.controller.Support;

import com.example.LoanMatrix.dto.Support.CreateSupportTicketRequest;
import com.example.LoanMatrix.dto.Support.SupportTicketResponse;
import com.example.LoanMatrix.dto.Support.UpdateTicketStatusRequest;
import com.example.LoanMatrix.entity.SupportTicket;
import com.example.LoanMatrix.response.ApiResponse;
import com.example.LoanMatrix.service.SupportService.SupportTicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/support")
@RequiredArgsConstructor
public class SupportTicketController {

    private final SupportTicketService supportTicketService;

    @PostMapping("/createTicket")
    public ResponseEntity<ApiResponse<SupportTicketResponse>> createTicket(@RequestParam Long customerId,
                                                                           @Valid @RequestBody CreateSupportTicketRequest request){
        SupportTicketResponse response = supportTicketService.createTicket(customerId, request);

        ApiResponse<SupportTicketResponse> apiResponse = ApiResponse.<SupportTicketResponse>builder()
                .success(true)
                .message("Support Ticket created Successfully")
                .data(response)
                .error(null)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

    }

    @GetMapping("/tickets")
    public ResponseEntity<ApiResponse<List<SupportTicketResponse>>> getCustomerTickets(@RequestParam Long customerId){
        List<SupportTicketResponse> ticket = supportTicketService.getCustomerTickets(customerId);

        ApiResponse<List<SupportTicketResponse>> apiResponse = ApiResponse.<List<SupportTicketResponse>>builder()
                .success(true)
                .message("Support ticket fetched Successfully")
                .data(ticket)
                .error(null)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/tickets/{ticketId}")
    public ResponseEntity<ApiResponse<SupportTicketResponse>> getTicket(
            @PathVariable Long ticketId,
            @RequestParam Long customerId) {

        SupportTicketResponse response =
                supportTicketService.getTicket(ticketId, customerId);

        ApiResponse<SupportTicketResponse> apiResponse =
                ApiResponse.<SupportTicketResponse>builder()
                        .success(true)
                        .message("Support ticket fetched successfully")
                        .data(response)
                        .error(null)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/admin/tickets")
    public ResponseEntity<ApiResponse<List<SupportTicketResponse>>> getAllTickets() {

        List<SupportTicketResponse> tickets =
                supportTicketService.getAllTickets();

        ApiResponse<List<SupportTicketResponse>> apiResponse =
                ApiResponse.<List<SupportTicketResponse>>builder()
                        .success(true)
                        .message("All support tickets fetched successfully")
                        .data(tickets)
                        .error(null)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/admin/tickets/{ticketId}")
    public ResponseEntity<ApiResponse<SupportTicketResponse>> getAdminTicket(
            @PathVariable Long ticketId) {

        SupportTicketResponse response = supportTicketService.getAdminTicket(ticketId);

        ApiResponse<SupportTicketResponse> apiResponse =
                ApiResponse.<SupportTicketResponse>builder()
                        .success(true)
                        .message("Support ticket fetched successfully")
                        .data(response)
                        .error(null)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PatchMapping("/admin/tickets/{ticketId}/status")
    public ResponseEntity<ApiResponse<SupportTicketResponse>> updateTicketStatus(
            @PathVariable Long ticketId,
            @Valid @RequestBody UpdateTicketStatusRequest request) {

        SupportTicketResponse response = supportTicketService.updateTicketStatus(ticketId, request.getStatus());

        ApiResponse<SupportTicketResponse> apiResponse =
                ApiResponse.<SupportTicketResponse>builder()
                        .success(true)
                        .message("Support ticket status updated successfully")
                        .data(response)
                        .error(null)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }
}
