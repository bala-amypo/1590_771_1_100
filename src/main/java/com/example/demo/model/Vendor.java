package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendors")
public class Vendor {

    @Id @GeneratedValue
    private Long id;

    private String vendorName;
    private String industry;
    private LocalDateTime createdAt;

    @ManyToMany
    private Set<DocumentType> supportedDocumentTypes = new HashSet<>();

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public Vendor() {}

    public Vendor(String vendorName, String industry, LocalDateTime createdAt,
            Set<DocumentType> supportedDocumentTypes) {
        this.vendorName = vendorName;
        this.industry = industry;
        this.createdAt = createdAt;
        this.supportedDocumentTypes = supportedDocumentTypes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<DocumentType> getSupportedDocumentTypes() {
        return supportedDocumentTypes;
    }

    public void setSupportedDocumentTypes(Set<DocumentType> supportedDocumentTypes) {
        this.supportedDocumentTypes = supportedDocumentTypes;
    }

    
    

}
