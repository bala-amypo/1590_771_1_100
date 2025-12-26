package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "compliance_scores")
public class ComplianceScore {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private Vendor vendor;

    private Double scoreValue;
    private String rating;
    private LocalDateTime lastEvaluated;

    public ComplianceScore() {}

    public ComplianceScore(Long id, Vendor vendor, Double scoreValue, String rating, LocalDateTime lastEvaluated) {
        this.id = id;
        this.vendor = vendor;
        this.scoreValue = scoreValue;
        this.rating = rating;
        this.lastEvaluated = lastEvaluated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public LocalDateTime getLastEvaluated() {
        return lastEvaluated;
    }

    public void setLastEvaluated(LocalDateTime lastEvaluated) {
        this.lastEvaluated = lastEvaluated;
    }

    
    

}
