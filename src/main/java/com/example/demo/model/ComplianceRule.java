package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compliance_rules")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplianceRule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String ruleName;
    private String ruleDescription;
    private String matchType;
    private Double threshold;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if(createdAt == null) createdAt = LocalDateTime.now();
        if(threshold == null) threshold = 0.0;
    }
}