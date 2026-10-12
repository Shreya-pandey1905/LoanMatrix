
package com.example.LoanMatrix.dto.customerDashboardDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDocumentResponse {

    private Long documentId;

    private String documentType;

    private String verificationStatus;
}
