package com.example.demo.controller;

import com.example.demo.model.ComplianceRule;
import com.example.demo.service.ComplianceRuleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compliance-rules")
@SecurityRequirement(name = "bearerAuth")
public class ComplianceRuleController {

    private final ComplianceRuleService complianceRuleService;

    public ComplianceRuleController(ComplianceRuleService complianceRuleService) {
        this.complianceRuleService = complianceRuleService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ComplianceRule createRule(@RequestBody ComplianceRule rule) {
        return complianceRuleService.createRule(rule);
    }

    @GetMapping
    public List<ComplianceRule> getAllRules() {
        return complianceRuleService.getAllRules();
    }

    @GetMapping("/{id}")
    public ComplianceRule getRuleById(@PathVariable Long id) {
        return complianceRuleService.getRule(id);
    }
}