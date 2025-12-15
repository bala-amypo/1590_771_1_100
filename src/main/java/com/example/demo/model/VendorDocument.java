package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VendorDocument {

    private long id;
    private Vendor vendor;
    private DocumentType documentType;
    private LocalDateTime uploadedAt;
    private LocalDate expiryDate;
    private Boolean isValid;

    public VendorDocument(){}

    public VendorDocument(Vendor vendor, DocumentType documentType, LocalDateTime uploadedAt, LocalDate expiryDate,
            Boolean isValid) {
        this.vendor = vendor;
        this.documentType = documentType;
        this.uploadedAt = uploadedAt;
        this.expiryDate = expiryDate;
        this.isValid = isValid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    
    
}
