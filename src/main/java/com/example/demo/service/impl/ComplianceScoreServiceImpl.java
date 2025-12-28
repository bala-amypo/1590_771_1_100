package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.util.ComplianceScoringEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplianceScoreServiceImpl {
    private final VendorRepository vendorRepo;
    private final DocumentTypeRepository typeRepo;
    private final VendorDocumentRepository docRepo;
    private final ComplianceScoreRepository scoreRepo;
    private final ComplianceScoringEngine engine = new ComplianceScoringEngine();

    public ComplianceScore evaluateVendor(Long vendorId) {
        Vendor v = vendorRepo.findById(vendorId).orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
        
        List<DocumentType> requiredTypes = typeRepo.findByRequiredTrue();
        List<VendorDocument> uploadedDocs = docRepo.findByVendor(v);
        
        double scoreValue = engine.calculateScore(requiredTypes, uploadedTypes(uploadedDocs));
        String rating = engine.deriveRating(scoreValue);
        
        ComplianceScore score = scoreRepo.findByVendor_Id(vendorId).orElse(new ComplianceScore());
        score.setVendor(v);
        score.setScoreValue(scoreValue);
        score.setRating(rating);
        score.setLastEvaluated(LocalDateTime.now());
        
        return scoreRepo.save(score);
    }

    public ComplianceScore getScore(Long vendorId) {
        return scoreRepo.findByVendor_Id(vendorId).orElseThrow(() -> new ResourceNotFoundException("Score not found"));
    }
    
    public List<ComplianceScore> getAllScores() { return scoreRepo.findAll(); }

    private List<DocumentType> uploadedTypes(List<VendorDocument> docs) {
        return docs.stream().filter(d -> Boolean.TRUE.equals(d.getIsValid())).map(VendorDocument::getDocumentType).toList();
    }
}