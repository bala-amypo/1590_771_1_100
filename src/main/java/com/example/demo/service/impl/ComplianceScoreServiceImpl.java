package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.util.ComplianceScoringEngine;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplianceScoreServiceImpl {

    private final VendorRepository vendorRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final VendorDocumentRepository vendorDocumentRepository;
    private final ComplianceScoreRepository complianceScoreRepository;

    private final ComplianceScoringEngine scoringEngine = new ComplianceScoringEngine();

    public ComplianceScoreServiceImpl(
            VendorRepository vendorRepository,
            DocumentTypeRepository documentTypeRepository,
            VendorDocumentRepository vendorDocumentRepository,
            ComplianceScoreRepository complianceScoreRepository) {

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

        long validCount = documents.stream()
                .filter(VendorDocument::getIsValid)
                .count();

        double score;
        if (requiredTypes.isEmpty()) {
            score = 100.0;
        } else {
            score = (validCount * 100.0) / requiredTypes.size();
        }

        ComplianceScore complianceScore =
                complianceScoreRepository.findByVendor_Id(vendorId)
                        .orElse(new ComplianceScore());

        complianceScore.setVendor(vendor);
        complianceScore.setScoreValue(score);
        complianceScore.setRating(scoringEngine.deriveRating(score));
        complianceScore.setLastEvaluated(LocalDateTime.now());

        return complianceScoreRepository.save(complianceScore);
    }

    public ComplianceScore getScore(Long vendorId) {
        return complianceScoreRepository.findByVendor_Id(vendorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Score not found"));
    }

    public List<ComplianceScore> getAllScores() {
        return complianceScoreRepository.findAll();
    }
}
