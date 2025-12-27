package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "vendor_documents")
public class VendorDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Test 34: Validates ManyToOne for documentType
   @ManyToOne
   @JoinColumn(name = "document_type_id")
   private DocumentType documentType;


    // Test 33: Validates ManyToOne for vendor
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    @JsonIgnoreProperties("supportedDocumentTypes")
    private Vendor vendor;

    private String fileUrl;
    private LocalDate expiryDate;
    private LocalDateTime uploadedAt;
    
    // Required for Test 60 and Test 61 logic
    private Boolean isValid; 
    private String status;

    public VendorDocument() {}

    public VendorDocument(Long id, Vendor vendor, DocumentType documentType, String fileUrl, LocalDateTime uploadedAt, LocalDate expiryDate, Boolean isValid) {
        this.id = id;
        this.vendor = vendor;
        this.documentType = documentType;
        this.fileUrl = fileUrl;
        this.uploadedAt = uploadedAt;
        this.expiryDate = expiryDate;
        this.isValid = isValid;
    }

    @PrePersist // Required for Test 29
    public void prePersist() {
        this.uploadedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = "PENDING";
        }
    }

    // --- GETTERS AND SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public DocumentType getDocumentType() { return documentType; }
    public void setDocumentType(DocumentType documentType) { this.documentType = documentType; }

    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }

    public Boolean getIsValid() { return isValid; }
    public void setIsValid(Boolean isValid) { this.isValid = isValid; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}