package com.example.demo.util;

import com.example.demo.model.DocumentType;

import java.util.List;

public class ComplianceScoringEngine {

    
    public double calculateScore(List<DocumentType> required, List<DocumentType> provided) {
        if (required == null || required.isEmpty()) {
            return 100.0;
        }
        long validCount = provided == null ? 0 :
                provided.stream().filter(dt -> dt != null).count();
        return ((double) validCount / required.size()) * 100.0;
    }

    
    public String deriveRating(double score) {
        if (score >= 90.0) return "EXCELLENT";
        if (score >= 75.0) return "GOOD";
        if (score >= 50.0) return "POOR";
        return "NON_COMPLIANT";
    }
}
