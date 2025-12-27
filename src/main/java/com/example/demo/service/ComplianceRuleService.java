package com.example.demo.service;

import com.example.demo.model.ComplianceRule;
import java.util.List;

public interface ComplianceRuleService {
    ComplianceRule createRule(ComplianceRule rule); [cite: 279]
    List<ComplianceRule> getAllRules(); [cite: 280]
    ComplianceRule getRule(Long id); [cite: 282]
}