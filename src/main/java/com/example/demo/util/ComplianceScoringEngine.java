package com.example.demo.util;

import com.example.demo.model.DocumentType;
import com.example.demo.model.VendorDocument;
import java.util.List;

public class ComplianceScoringEngine {
    public double calculateScore(List<DocumentType> required, List<VendorDocument> uploaded) {
        if (required.isEmpty()) return 100.0;
        double totalWeight = required.stream().mapToDouble(DocumentType::getWeight).sum();
        double achievedWeight = 0;
        for (DocumentType type : required) {
            boolean valid = uploaded.stream()
                .anyMatch(d -> d.getDocumentType().getId().equals(type.getId()) && d.getIsValid());
            if (valid) achievedWeight += type.getWeight();
        }
        return (achievedWeight / totalWeight) * 100.0;
    }

    public String deriveRating(double score) {
        if (score >= 90) return "EXCELLENT";
        if (score >= 75) return "GOOD";
        if (score >= 50) return "POOR";
        return "NONCOMPLIANT";
    }
}