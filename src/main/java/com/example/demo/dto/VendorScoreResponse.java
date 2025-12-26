package com.example.demo.dto;

public class VendorScoreResponse {

    private Long vendorId;
    private Double score;
    private String rating;

    public VendorScoreResponse() {
    }

    public VendorScoreResponse(Long vendorId, Double score, String rating) {
        this.vendorId = vendorId;
        this.score = score;
        this.rating = rating;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public Double getScore() {
        return score;
    }

    public String getRating() {
        return rating;
    }
}
