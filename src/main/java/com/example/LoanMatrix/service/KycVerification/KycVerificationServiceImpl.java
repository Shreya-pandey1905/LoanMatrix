package com.example.LoanMatrix.service.KycVerification;

import com.example.LoanMatrix.entity.Customer;
import com.example.LoanMatrix.entity.KycDocument;

import com.example.LoanMatrix.repository.KycDocumentRepository.CustomerRepository;
import com.example.LoanMatrix.repository.KycDocumentRepository.KycDocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import java.util.List;


@Service
public class KycVerificationServiceImpl implements KycVerificationService {

    private final KycDocumentRepository kycDocumentRepository;
    private final CustomerRepository customerRepository;

    public KycVerificationServiceImpl(KycDocumentRepository kycDocumentRepository,
                                      CustomerRepository customerRepository) {
        this.kycDocumentRepository = kycDocumentRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public KycDocument uploadDocument(Long customerId, String documentType, MultipartFile file) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path uploadPath = Paths.get("src/main/resources/uploads");

            Files.createDirectories(uploadPath);

            Path filePath = uploadPath.resolve(fileName);

            Files.write(filePath, file.getBytes());

            KycDocument document = new KycDocument();
            document.setCustomer(customer);
            document.setDocumentType(documentType);
            document.setFilePath(filePath.toString());
            document.setVerificationStatus("PENDING");

            return kycDocumentRepository.save(document);

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload document");
        }
    }

    @Override
    public List<KycDocument> getCustomerDocuments(Long customerId) {
        return kycDocumentRepository.findByCustomerId(customerId);
    }

    @Override
    public List<KycDocument> getPendingDocuments() {
        return kycDocumentRepository.findByVerificationStatus("PENDING");
    }

    @Override
    public KycDocument approveDocument(Long documentId) {
        KycDocument document = kycDocumentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("KYC document not found"));

        document.setVerificationStatus("APPROVED");

        return kycDocumentRepository.save(document);
    }

    @Override
    public KycDocument rejectDocument(Long documentId) {
        KycDocument document = kycDocumentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("KYC document not found"));

        document.setVerificationStatus("REJECTED");

        return kycDocumentRepository.save(document);
    }

    @Override
    public byte[] viewDocument(Long documentId) {

        KycDocument document = kycDocumentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("KYC document not found"));

        try {
            Path path = Paths.get(document.getFilePath());
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("Unable to view document");
        }
    }
}