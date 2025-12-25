package com.example.demo.util;

import com.example.demo.model.DocumentType;
import com.example.demo.model.VendorDocument;

import java.util.List;

public class ComplianceScoringEngine {
    
    public double calculateScore(List<DocumentType> requiredTypes, List<VendorDocument> vendorDocuments) {
        if (requiredTypes.isEmpty()) {
            return 100.0;
        }
        
        double totalWeight = requiredTypes.stream()
                .mapToInt(DocumentType::getWeight)
                .sum();
        
        if (totalWeight == 0) {
            return 0.0;
        }
        
        double achievedWeight = 0;
        
        for (VendorDocument doc : vendorDocuments) {
            if (doc.getIsValid() != null && doc.getIsValid()) {
                DocumentType type = doc.getDocumentType();
                if (requiredTypes.contains(type)) {
                    achievedWeight += type.getWeight();
                }
            }
        }
        
        return (achievedWeight / totalWeight) * 100.0;
    }
    
    public String deriveRating(double score) {
        if (score >= 90) {
            return "EXCELLENT";
        } else if (score >= 70) {
            return "GOOD";
        } else if (score >= 50) {
            return "POOR";
        } else {
            return "NON_COMPLIANT";
        }
    }
}