package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ComplianceScoreService;
import com.example.demo.util.ComplianceScoringEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComplianceScoreServiceImpl implements ComplianceScoreService {
    private final VendorRepository vendorRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final VendorDocumentRepository vendorDocumentRepository;
    private final ComplianceScoreRepository complianceScoreRepository;
    
    // Engine can be instantiated here or injected if made a Bean. 
    // Tests instantiate Service manually, so we use `new` internally or via field.
    private final ComplianceScoringEngine engine = new ComplianceScoringEngine();

    @Override
    public ComplianceScore evaluateVendor(Long vendorId) {
        Vendor v = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
        
        List<DocumentType> requiredTypes = documentTypeRepository.findByRequiredTrue();
        List<VendorDocument> uploadedDocs = vendorDocumentRepository.findByVendor(v);
        
        List<DocumentType> uploadedTypes = uploadedDocs.stream()
                .filter(d -> Boolean.TRUE.equals(d.getIsValid()))
                .map(VendorDocument::getDocumentType)
                .collect(Collectors.toList());

        double scoreVal = engine.calculateScore(requiredTypes, uploadedTypes);
        String rating = engine.deriveRating(scoreVal);
        
        ComplianceScore score = complianceScoreRepository.findByVendor_Id(vendorId)
                .orElse(new ComplianceScore());
        
        score.setVendor(v);
        score.setScoreValue(scoreVal);
        score.setRating(rating);
        score.setLastEvaluated(LocalDateTime.now());
        
        return complianceScoreRepository.save(score);
    }

    @Override
    public ComplianceScore getScore(Long vendorId) {
        return complianceScoreRepository.findByVendor_Id(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Score not found"));
    }

    @Override
    public List<ComplianceScore> getAllScores() {
        return complianceScoreRepository.findAll();
    }
}