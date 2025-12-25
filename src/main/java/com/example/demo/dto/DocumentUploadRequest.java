package com.example.demo.dto;

import java.time.LocalDate;

public class DocumentUploadRequest {

    private String fileUrl;
    private LocalDate expiryDate;

    public DocumentUploadRequest() {
    }

    public String getFileUrl() {
        return fileUrl;
    }
 
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
 
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
 
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
