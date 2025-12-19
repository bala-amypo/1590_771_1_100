package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendor_documents")
public class VendorDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;

    @Column(nullable = false)
    private String fileUrl;

    @Column(nullable = false, updatable = false)
    private LocalDateTime uploadedAt;

    @Column
    private LocalDateTime expiryDate;

    @Column(nullable = false)
    private Boolean isValid;


    @PrePersist
    protected void onCreate() {
        this.uploadedAt = LocalDateTime.now();
        evaluateValidity();
    }

    @PreUpdate
    protected void onUpdate() {
        evaluateValidity();
    }

    private void evaluateValidity() {
        if (expiryDate == null || expiryDate.isAfter(LocalDateTime.now())) {
            this.isValid = true;
        } else {
            this.isValid = false;
        }
    }

    public VendorDocument(){}

    public VendorDocument(Vendor vendor, DocumentType documentType, String fileUrl, LocalDateTime uploadedAt,
            LocalDateTime expiryDate, Boolean isValid) {
        this.vendor = vendor;
        this.documentType = documentType;
        this.fileUrl = fileUrl;
        this.uploadedAt = uploadedAt;
        this.expiryDate = expiryDate;
        this.isValid = isValid;
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

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
    this.uploadedAt = uploadedAt;
}

public void setIsValid(Boolean isValid) {
    this.isValid = isValid;
}


}
