package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ComplianceScoreService;
import com.example.demo.util.ComplianceScoringEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Added for data consistency
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
                                      ComplianceScoreRepository complianceScoreRepository) {
        this.vendorRepository = vendorRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.vendorDocumentRepository = vendorDocumentRepository;
        this.complianceScoreRepository = complianceScoreRepository;
    }

    @Override
    @Transactional
    public ComplianceScore evaluateVendor(Long vendorId) {
        // 1. Verify vendor exists
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with ID: " + vendorId));
        
        // 2. Fetch required document types and uploaded documents
        List<DocumentType> requiredTypes = documentTypeRepository.findByRequiredTrue();
        List<VendorDocument> docs = vendorDocumentRepository.findByVendor(vendor);

        // 3. Calculate score using the engine
        double scoreVal = scoringEngine.calculateScore(requiredTypes, docs);
        if (scoreVal < 0) throw new ValidationException("Compliance score cannot be negative");

        // 4. Retrieve existing score or create new using the method required by the test suite
        ComplianceScore score = complianceScoreRepository.findByVendor_Id(vendorId)
                .orElse(new ComplianceScore());
        
        // 5. Update score attributes
        score.setVendor(vendor);
        score.setScoreValue(scoreVal);
        score.setRating(scoringEngine.deriveRating(scoreVal));
        score.setLastEvaluated(LocalDateTime.now());

        return complianceScoreRepository.save(score);
    }

    @Override
    public ComplianceScore getScore(Long vendorId) {
        // Uses the underscore naming convention to stay consistent with the test suite
        return complianceScoreRepository.findByVendor_Id(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Compliance score not found for vendor: " + vendorId));
    }

    @Override
    public List<ComplianceScore> getAllScores() { 
        return complianceScoreRepository.findAll(); 
    }
}