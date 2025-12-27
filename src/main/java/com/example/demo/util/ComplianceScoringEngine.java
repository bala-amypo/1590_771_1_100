package com.example.demo.util;

import com.example.demo.model.DocumentType;
import com.example.demo.model.VendorDocument;
import java.util.List;

public class ComplianceScoringEngine {

    /**
     * Calculates the score based on required document types and uploaded documents.
     * Validates logic for Tests 24, 60, 61, and 64.
     */
    public double calculateScore(List<DocumentType> requiredTypes, List<VendorDocument> uploadedDocs) {
        // Test 64: Edge case where no documents are required
        if (requiredTypes == null || requiredTypes.isEmpty()) {
            return 100.0;
        }

        double totalWeight = 0.0;
        double achievedWeight = 0.0;

        for (DocumentType type : requiredTypes) {
            totalWeight += type.getWeight();
            
            // Check if the vendor has uploaded a valid document for this specific type
            boolean hasValidDoc = uploadedDocs.stream()
                .anyMatch(doc -> doc.getDocumentType() != null && 
                                 doc.getDocumentType().getId().equals(type.getId()) && 
                                 Boolean.TRUE.equals(doc.getIsValid()));
            
            if (hasValidDoc) {
                achievedWeight += type.getWeight();
            }
        }

        if (totalWeight == 0) return 100.0;
        
        return (achievedWeight / totalWeight) * 100.0;
    }

    /**
     * Derives a string rating based on the calculated score.
     * Thresholds must match Test 63 exactly.
     */
    public String deriveRating(double score) {
        if (score >= 90.0) {
            return "EXCELLENT";
        } else if (score >= 70.0) {
            return "GOOD";
        } else if (score >= 40.0) {
            return "POOR";
        } else {
            return "NON_COMPLIANT";
        }
    }
}