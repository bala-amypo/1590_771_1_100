package com.example.demo.controller;

import com.example.demo.model.ComplianceRule;
import com.example.demo.service.impl.ComplianceRuleServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compliance-rules")
public class ComplianceRuleController {

    private final ComplianceRuleServiceImpl ruleService;

    public ComplianceRuleController(ComplianceRuleServiceImpl ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<ComplianceRule> create(@RequestBody ComplianceRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @GetMapping
    public ResponseEntity<List<ComplianceRule>> getAll() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplianceRule> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ruleService.getRule(id));
    }
}
