package com.example.demo.controller;

import com.example.demo.model.ComplianceRule;
import com.example.demo.service.ComplianceRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/compliance-rules")
@RequiredArgsConstructor
public class ComplianceRuleController {
    private final ComplianceRuleService complianceRuleService;

    @PostMapping
    public ComplianceRule createRule(@RequestBody ComplianceRule rule) {
        return complianceRuleService.createRule(rule);
    }

    @GetMapping
    public List<ComplianceRule> getAllRules() {
        return complianceRuleService.getAllRules();
    }
    
    @GetMapping("/{id}")
    public ComplianceRule getRule(@PathVariable Long id) {
        return complianceRuleService.getRule(id);
    }
}