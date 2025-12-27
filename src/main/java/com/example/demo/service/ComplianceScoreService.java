package com.example.demo.service;

import com.example.demo.model.ComplianceScore;
import java.util.List;

public interface ComplianceScoreService {
    ComplianceScore evaluateVendor(Long vendorId); [cite: 289]
    ComplianceScore getScore(Long vendorId); [cite: 290]
    List<ComplianceScore> getAllScores(); [cite: 291]
}