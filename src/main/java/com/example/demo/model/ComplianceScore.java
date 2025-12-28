package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compliance_scores")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplianceScore {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private Vendor vendor;
    
    private Double scoreValue;
    private LocalDateTime lastEvaluated;
    private String rating;
}