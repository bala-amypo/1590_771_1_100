// package com.example.demo.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.model.ComplianceScore;
// import com.example.demo.service.ComplianceScoreService;

// import io.swagger.v3.oas.annotations.tags.Tag;

// @RestController
// @RequestMapping("/api/compliance-scores")
// @Tag(name = "Compliance Score Controller")
// public class ComplianceScoreController {

//     @Autowired
//     private ComplianceScoreService complianceScoreService;

//     @PostMapping("/evaluate/{vendorId}")
//     public ResponseEntity<ComplianceScore> evaluateVendor(@PathVariable Long vendorId) {
//         return ResponseEntity.ok(complianceScoreService.evaluateVendor(vendorId));
//     }

//     @GetMapping("/{vendorId}")
//     public ResponseEntity<ComplianceScore> getScore(@PathVariable Long vendorId) {
//         return ResponseEntity.ok(complianceScoreService.getScore(vendorId));
//     }

//     @GetMapping
//     public ResponseEntity<List<ComplianceScore>> getAllScores() {
//         return ResponseEntity.ok(complianceScoreService.getAllScores());
//     }
// }
