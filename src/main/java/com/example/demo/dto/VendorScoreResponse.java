package com.example.demo.dto;

import java.time.LocalDateTime;

public class VendorScoreResponse {
    private Long vendorId;
    private String vendorName;
    private Double scoreValue; 
    private String rating; 
    private LocalDateTime lastEvaluated;

    public VendorScoreResponse() {}

    
    public VendorScoreResponse(Long vendorId, String vendorName, Double scoreValue, String rating, LocalDateTime lastEvaluated) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.scoreValue = scoreValue;
        this.rating = rating;
        this.lastEvaluated = lastEvaluated;
    }

    
    public Long getVendorId() { return vendorId; }
    public void setVendorId(Long vendorId) { this.vendorId = vendorId; }

    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }

    public Double getScoreValue() { return scoreValue; }
    public void setScoreValue(Double scoreValue) { this.scoreValue = scoreValue; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }

    public LocalDateTime getLastEvaluated() { return lastEvaluated; }
    public void setLastEvaluated(LocalDateTime lastEvaluated) { this.lastEvaluated = lastEvaluated; }
}