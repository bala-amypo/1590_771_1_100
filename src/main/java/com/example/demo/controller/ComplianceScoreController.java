package com.example.demo.controller;

import com.example.demo.model.ComplianceScore;
import com.example.demo.service.ComplianceScoreService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compliance-scores")
@SecurityRequirement(name = "bearerAuth")
public class ComplianceScoreController {

    private final ComplianceScoreService complianceScoreService;

    public ComplianceScoreController(ComplianceScoreService complianceScoreService) {
        this.complianceScoreService = complianceScoreService;
    }

    @PostMapping("/evaluate")
    public ComplianceScore evaluateVendor(@RequestParam Long vendorId) {
        return complianceScoreService.evaluateVendor(vendorId);
    }

    @GetMapping("/vendor/{vendorId}")
    public ComplianceScore getVendorScore(@PathVariable Long vendorId) {
        return complianceScoreService.getScore(vendorId);
    }

    @GetMapping
    public List<ComplianceScore> getAllScores() {
        return complianceScoreService.getAllScores();
    }
}