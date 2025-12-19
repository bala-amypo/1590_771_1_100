package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ComplianceScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplianceScoreServiceImpl implements ComplianceScoreService {

    private final VendorRepository vendorRepository;
    private final VendorDocumentRepository vendorDocumentRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final ComplianceScoreRepository complianceScoreRepository;

    @Autowired
    public ComplianceScoreServiceImpl(
            VendorRepository vendorRepository,
            VendorDocumentRepository vendorDocumentRepository,
            DocumentTypeRepository documentTypeRepository,
            ComplianceScoreRepository complianceScoreRepository) {
        this.vendorRepository = vendorRepository;
        this.vendorDocumentRepository = vendorDocumentRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.complianceScoreRepository = complianceScoreRepository;
    }

    @Override
    public ComplianceScore evaluateVendor(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with id: " + vendorId));

        List<DocumentType> requiredTypes = documentTypeRepository.findAll()
                .stream().filter(DocumentType::getRequired).toList();

        List<VendorDocument> uploadedDocs = vendorDocumentRepository.findByVendor_Id(vendorId);

        double totalWeight = requiredTypes.stream().mapToDouble(DocumentType::getWeight).sum();
        double earnedWeight = 0;

        for (DocumentType type : requiredTypes) {
            boolean hasValidDoc = uploadedDocs.stream()
                    .anyMatch(doc -> doc.getDocumentType().getId().equals(type.getId()) && Boolean.TRUE.equals(doc.getIsValid()));
            if (hasValidDoc) {
                earnedWeight += type.getWeight();
            }
        }

        double score = totalWeight == 0 ? 100.0 : (earnedWeight / totalWeight) * 100;
        String rating = getRating(score);

        ComplianceScore scoreRecord = complianceScoreRepository.findByVendor_Id(vendorId);
        if (scoreRecord == null) {
            scoreRecord = new ComplianceScore();
            scoreRecord.setVendor(vendor);
        }

        scoreRecord.setScoreValue(score);
        scoreRecord.setRating(rating);
        scoreRecord.setLastEvaluated(LocalDateTime.now());

        return complianceScoreRepository.save(scoreRecord);
    }

    @Override
    public ComplianceScore getScore(Long vendorId) {
        ComplianceScore score = complianceScoreRepository.findByVendor_Id(vendorId);
        if (score == null) {
            throw new ResourceNotFoundException("Compliance score not found for vendor id: " + vendorId);
        }
        return score;
    }

    @Override
    public List<ComplianceScore> getAllScores() {
        return complianceScoreRepository.findAll();
    }

    private String getRating(double score) {
        if (score >= 90) return "EXCELLENT";
        if (score >= 75) return "GOOD";
        if (score >= 50) return "POOR";
        return "NON_COMPLIANT";
    }
}
