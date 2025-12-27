package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ComplianceScoreService;
import com.example.demo.util.ComplianceScoringEngine;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplianceScoreServiceImpl implements ComplianceScoreService {
    private final VendorRepository vendorRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final VendorDocumentRepository vendorDocumentRepository;
    private final ComplianceScoreRepository complianceScoreRepository;
    private final ComplianceScoringEngine scoringEngine = new ComplianceScoringEngine();

    public ComplianceScoreServiceImpl(VendorRepository vendorRepository,
                                      DocumentTypeRepository documentTypeRepository,
                                      VendorDocumentRepository vendorDocumentRepository,
                                      ComplianceScoreRepository complianceScoreRepository) { // [cite: 55, 56, 57, 58]
        this.vendorRepository = vendorRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.vendorDocumentRepository = vendorDocumentRepository;
        this.complianceScoreRepository = complianceScoreRepository;
    }

    @Override
    public ComplianceScore evaluateVendor(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found")); // [cite: 295]

        List<DocumentType> requiredTypes = documentTypeRepository.findByRequiredTrue(); // [cite: 297]
        List<VendorDocument> docs = vendorDocumentRepository.findByVendor(vendor);

        double scoreVal = scoringEngine.calculateScore(requiredTypes, docs);
        if (scoreVal < 0) {
            throw new ValidationException("Compliance score cannot be negative"); // [cite: 230, 299]
        }

        ComplianceScore score = complianceScoreRepository.findByVendorId(vendorId)
                .orElse(new ComplianceScore());
        
        score.setVendor(vendor);
        score.setScoreValue(scoreVal);
        score.setRating(scoringEngine.deriveRating(scoreVal)); // [cite: 172, 300]
        score.setLastEvaluated(LocalDateTime.now()); // [cite: 300]

        return complianceScoreRepository.save(score);
    }

    @Override
    public ComplianceScore getScore(Long vendorId) {
        return complianceScoreRepository.findByVendorId(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Score not found")); // [cite: 220]
    }

    @Override
    public List<ComplianceScore> getAllScores() {
        return complianceScoreRepository.findAll();
    }
}