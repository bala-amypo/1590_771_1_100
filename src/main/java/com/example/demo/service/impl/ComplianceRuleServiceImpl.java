package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ComplianceRule;
import com.example.demo.repository.ComplianceRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplianceRuleServiceImpl {
    private final ComplianceRuleRepository repo;

    public ComplianceRule createRule(ComplianceRule r) { return repo.save(r); }
    public ComplianceRule getRule(Long id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rule not found")); }
    public List<ComplianceRule> getAllRules() { return repo.findAll(); }
}