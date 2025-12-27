package com.example.demo.util;

import com.example.demo.model.DocumentType;
import com.example.demo.model.VendorDocument;
import java.util.List;

public class ComplianceScoringEngine {

    /**
     * Calculates a weighted percentage (0-100) based on required document types.
     * Logic used in test priority 24, 60, 61, and 64.
     */
    public double calculateScore(List<DocumentType> requiredTypes, List<VendorDocument> uploadedDocs) {
        // If no document types are required, the vendor is 100% compliant 
        if (requiredTypes == null || requiredTypes.isEmpty()) {
            return 100.0;
        }

        double totalWeight = 0;
        double achievedWeight = 0;

        for (DocumentType type : requiredTypes) {
            totalWeight += type.getWeight(); // [cite: 111, 297]
            
            // Check if there is a valid uploaded document for this specific type
            boolean hasValidDoc = uploadedDocs.stream()
                    .anyMatch(doc -> doc.getDocumentType().getId().equals(type.getId()) && 
                                    Boolean.TRUE.equals(doc.getIsValid())); // [cite: 137, 297]

            if (hasValidDoc) {
                achievedWeight += type.getWeight();
            }
        }

        if (totalWeight == 0) return 100.0;
        
        double score = (achievedWeight / totalWeight) * 100.0;
        return Math.max(0, Math.min(100, score)); // Ensure result is 0-100 [cite: 163, 297]
    }

    /**
     * Derives a string rating from the calculated score.
     * Used for test priority 63.
     */
    public String deriveRating(double score) {
        if (score >= 90) return "EXCELLENT"; // 
        if (score >= 75) return "GOOD";      // 
        if (score >= 50) return "POOR";      // 
        return "NONCOMPLIANT";               // 
    }
}