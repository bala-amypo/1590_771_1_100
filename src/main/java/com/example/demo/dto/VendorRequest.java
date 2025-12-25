package com.example.demo.dto;

public class VendorRequest {

    private String vendorName;
    private String industry;

    public VendorRequest() {
    }

    public String getVendorName() {
        return vendorName;
    }
 
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
 
    public String getIndustry() {
        return industry;
    }
 
    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
