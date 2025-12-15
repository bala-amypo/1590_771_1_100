package com.example.demo.model;

import java.time.LocalDateTime;


public class ComplianceRule {

    private long id;
    private String ruleName;
    private String ruleDescription;
    private String matchType;
    private Double threshold;
    private LocalDateTime createdAt;

    public ComplianceRule(){}

    public ComplianceRule(String ruleName, String ruleDescription, String matchType, Double threshold,
            LocalDateTime createdAt) {
        this.ruleName = ruleName;
        this.ruleDescription = ruleDescription;
        this.matchType = matchType;
        this.threshold = threshold;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    




    
}
