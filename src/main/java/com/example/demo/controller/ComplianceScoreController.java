package com.example.demo.controller;

import com.example.demo.model.ComplianceScore;
import com.example.demo.service.impl.ComplianceScoreServiceImpl;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compliance-scores")
public class ComplianceScoreController {

    private final ComplianceScoreServiceImpl complianceScoreService;

    public ComplianceScoreController(ComplianceScoreServiceImpl complianceScoreService) {
        this.complianceScoreService = complianceScoreService;
    }

    @PostMapping("/evaluate")
    public ComplianceScore evaluate(@RequestParam Long vendorId) {
        return complianceScoreService.evaluateVendor(vendorId);
    }

    @GetMapping("/vendor/{vendorId}")
    public ComplianceScore getByVendor(@PathVariable Long vendorId) {
        return complianceScoreService.getScore(vendorId);
    }

    @GetMapping
    public List<ComplianceScore> getAll() {
        return complianceScoreService.getAllScores();
    }
}
