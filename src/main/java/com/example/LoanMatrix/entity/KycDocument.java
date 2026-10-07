package com.example.LoanMatrix.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kyc_documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KycDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KycDocumentId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId", nullable = false)
    private Customer customer;

    @Column(name = "DocumentType", nullable = false)
    private String documentType;

    @Column(name = "FilePath", nullable = false)
    private String filePath;

    @Column(name = "VerificationStatus")
    private String verificationStatus;
}