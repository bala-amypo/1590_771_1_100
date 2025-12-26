package com.example.demo.util;

import com.example.demo.model.DocumentType;

import java.util.List;

public class ComplianceScoringEngine {

    /**
     * Calculates compliance score as a percentage (0–100).
     *
     * @param requiredTypes   all required document types
     * @param submittedTypes document types submitted by vendor
     * @return compliance score
     */
    public double calculateScore(List<DocumentType> requiredTypes,
                                 List<DocumentType> submittedTypes) {

        if (requiredTypes == null || requiredTypes.isEmpty()) {
            return 100.0;
        }

        long matchedCount = submittedTypes.stream()
                .filter(requiredTypes::contains)
                .count();

        return (matchedCount * 100.0) / requiredTypes.size();
    }

    
    public String deriveRating(double score) {

        if (score >= 90) {
            return "EXCELLENT";
        }
        if (score >= 75) {
            return "GOOD";
        }
        if (score >= 50) {
            return "POOR";
        }
        return "NON_COMPLIANT";
    }
}
