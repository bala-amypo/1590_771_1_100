package com.example.demo.controller;

import com.example.demo.model.ComplianceScore;
import com.example.demo.service.ComplianceScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/compliance-scores")
@RequiredArgsConstructor
public class ComplianceScoreController {
    private final ComplianceScoreService complianceScoreService;

    @PostMapping("/evaluate")
    public ComplianceScore evaluateVendor(@RequestParam Long vendorId) {
        return complianceScoreService.evaluateVendor(vendorId);
    }

    @GetMapping("/vendor/{vendorId}")
    public ComplianceScore getScore(@PathVariable Long vendorId) {
        return complianceScoreService.getScore(vendorId);
    }

    @GetMapping
    public List<ComplianceScore> getAllScores() {
        return complianceScoreService.getAllScores();
    }
}