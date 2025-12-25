package com.example.demo.dto;

public class ComplianceScoreResponse {

    private Long vendorId;
    private double scoreValue;
    private String rating;

    public ComplianceScoreResponse() {
    }

    public ComplianceScoreResponse(Long vendorId, double scoreValue, String rating) {
        this.vendorId = vendorId;
        this.scoreValue = scoreValue;
        this.rating = rating;
    }

    public Long getVendorId() {
        return vendorId;
    }
 
    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }
 
    public double getScoreValue() {
        return scoreValue;
    }
 
    public void setScoreValue(double scoreValue) {
        this.scoreValue = scoreValue;
    }
 
    public String getRating() {
        return rating;
    }
 
    public void setRating(String rating) {
        this.rating = rating;
    }
}
