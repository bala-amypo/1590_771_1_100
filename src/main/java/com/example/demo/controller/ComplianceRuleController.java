package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ComplianceRule;
import com.example.demo.service.ComplianceRuleService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/compliance-rules")
@Tag(name = "Compliance Rule Controller")
public class ComplianceRuleController {

    @Autowired
    private ComplianceRuleService complianceRuleService;

    @PostMapping
    public ResponseEntity<ComplianceRule> createRule(@RequestBody ComplianceRule rule) {
        return ResponseEntity.ok(complianceRuleService.createRule(rule));
    }

    @GetMapping
    public ResponseEntity<List<ComplianceRule>> getAllRules() {
        return ResponseEntity.ok(complianceRuleService.getAllRules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplianceRule> getRule(@PathVariable Long id) {
        return ResponseEntity.ok(complianceRuleService.getRule(id));
    }
}

