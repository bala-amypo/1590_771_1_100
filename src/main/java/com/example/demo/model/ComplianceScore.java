package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compliance_scores")
public class ComplianceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "vendor_id", unique = true, nullable = false)
    private Vendor vendor;

    @Column(nullable = false)
    private Double scoreValue;

    @Column(nullable = false)
    private LocalDateTime lastEvaluated;

    @Column(nullable = false)
    private String rating; 

    
    @PrePersist
    @PreUpdate
    protected void validateScore() {
        if (scoreValue == null || scoreValue < 0 || scoreValue > 100) {
            throw new IllegalArgumentException("Score value must be between 0 and 100");
        }
    }

    public ComplianceScore(){}
    

    public ComplianceScore(Vendor vendor, Double scoreValue, LocalDateTime lastEvaluated, String rating) {
        this.vendor = vendor;
        this.scoreValue = scoreValue;
        this.lastEvaluated = lastEvaluated;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Double getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(Double scoreValue) {
        this.scoreValue = scoreValue;
    }

    public LocalDateTime getLastEvaluated() {
        return lastEvaluated;
    }

    public void setLastEvaluated(LocalDateTime lastEvaluated) {
        this.lastEvaluated = lastEvaluated;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
