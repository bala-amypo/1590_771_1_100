package com.example.demo.util;

import com.example.demo.model.DocumentType;
import java.util.List;

public class ComplianceScoringEngine {
    public double calculateScore(List<DocumentType> required, List<DocumentType> uploaded) {
        if(required.isEmpty()) return 100.0;
        long matchCount = required.stream().filter(uploaded::contains).count();
        return ((double) matchCount / required.size()) * 100.0;
    }

    public String deriveRating(double score) {
        if (score >= 95) return "EXCELLENT";
        if (score >= 80) return "GOOD";
        if (score >= 60) return "POOR";
        return "NON_COMPLIANT";
    }
}