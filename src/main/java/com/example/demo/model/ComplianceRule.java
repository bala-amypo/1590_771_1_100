package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "compliance_rules",
    uniqueConstraints = @UniqueConstraint(columnNames = "ruleName")
)
public class ComplianceRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleName;

    @Column
    private String ruleDescription;

    @Column(nullable = false)
    private String matchType; 

    @Column(nullable = false)
    private Double threshold;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreateAndValidate() {
        this.createdAt = LocalDateTime.now();
        validateThreshold();
    }

    @PreUpdate
    protected void onUpdateValidate() {
        validateThreshold();
    }

    private void validateThreshold() {
        if (threshold == null || threshold < 0) {
            throw new IllegalArgumentException("Threshold must be greater than or equal to 0");
        }
    }

    public ComplianceRule(){}


    public ComplianceRule(String ruleName, String ruleDescription, String matchType, Double threshold,
            LocalDateTime createdAt) {
        this.ruleName = ruleName;
        this.ruleDescription = ruleDescription;
        this.matchType = matchType;
        this.threshold = threshold;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
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
}
