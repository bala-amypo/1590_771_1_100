package com.example.demo.dto;

import lombok.Data;

@Data
public class ComplianceRuleDTO {
    private Long id;
    private String ruleName;
    private String ruleDescription;
    private String matchType;
    private Double threshold;
}