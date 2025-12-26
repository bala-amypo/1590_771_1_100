package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ComplianceRule;
import com.example.demo.repository.ComplianceRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplianceRuleServiceImpl {

    private final ComplianceRuleRepository complianceRuleRepository;

    public ComplianceRuleServiceImpl(ComplianceRuleRepository complianceRuleRepository) {
        this.complianceRuleRepository = complianceRuleRepository;
    }

    public ComplianceRule createRule(ComplianceRule rule) {
        return complianceRuleRepository.save(rule);
    }

    public List<ComplianceRule> getAllRules() {
        return complianceRuleRepository.findAll();
    }

    public ComplianceRule getRule(Long id) {
        return complianceRuleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rule not found"));
    }
}
