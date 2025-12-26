package com.example.demo.controller;

import com.example.demo.model.ComplianceScore;
import com.example.demo.service.impl.ComplianceScoreServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compliance-scores")
public class ComplianceScoreController {

    private final ComplianceScoreServiceImpl scoreService;

    public ComplianceScoreController(ComplianceScoreServiceImpl scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/evaluate")
    public ResponseEntity<ComplianceScore> evaluate(
            @RequestParam Long vendorId) {

        return ResponseEntity.ok(scoreService.evaluateVendor(vendorId));
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<ComplianceScore> getByVendor(
            @PathVariable Long vendorId) {

        return ResponseEntity.ok(scoreService.getScore(vendorId));
    }

    @GetMapping
    public ResponseEntity<List<ComplianceScore>> getAll() {
        return ResponseEntity.ok(scoreService.getAllScores());
    }
}
