package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplianceScoreServiceImpl {

    private final VendorRepository vendorRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final VendorDocumentRepository vendorDocumentRepository;
    private final ComplianceScoreRepository complianceScoreRepository;

    public ComplianceScoreServiceImpl(
            VendorRepository vendorRepository,
            DocumentTypeRepository documentTypeRepository,
            VendorDocumentRepository vendorDocumentRepository,
            ComplianceScoreRepository complianceScoreRepository
    ) {
        this.vendorRepository = vendorRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.vendorDocumentRepository = vendorDocumentRepository;
        this.complianceScoreRepository = complianceScoreRepository;
    }

    public ComplianceScore evaluateVendor(Long vendorId) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vendor not found"));

        List<DocumentType> requiredTypes =
                documentTypeRepository.findByRequiredTrue();

        List<VendorDocument> documents =
                vendorDocumentRepository.findByVendor(vendor);

        double score;

        if (requiredTypes.isEmpty()) {
            score = 100.0;
        } else {
            long validCount = documents.stream()
                    .filter(d -> Boolean.TRUE.equals(d.getIsValid()))
                    .count();
            score = (validCount * 100.0) / requiredTypes.size();
        }

        ComplianceScore cs = new ComplianceScore();
        cs.setVendor(vendor);
        cs.setScoreValue(score);

        cs.setRating(
                score >= 90 ? "EXCELLENT" :
                score >= 75 ? "GOOD" :
                score >= 50 ? "POOR" : "NON_COMPLIANT"
        );

        return complianceScoreRepository.save(cs);
    }

    public ComplianceScore getScore(Long vendorId) {
        return complianceScoreRepository.findByVendor_Id(vendorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Score not found"));
    }
}
