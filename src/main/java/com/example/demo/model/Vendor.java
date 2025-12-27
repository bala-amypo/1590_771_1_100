package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "vendors")
public class Vendor {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String vendorName;

    private String email;
    private String phone;
    private String industry;
    private LocalDateTime createdAt;

    // FetchType.EAGER is often necessary for automated test suites to see related data
    @ManyToMany(fetch = FetchType.EAGER) 
    @JoinTable(
        name = "vendor_document_types",
        joinColumns = @JoinColumn(name = "vendor_id"),
        inverseJoinColumns = @JoinColumn(name = "document_type_id")
    )
    @JsonIgnoreProperties("vendors")
    private Set<DocumentType> supportedDocumentTypes = new HashSet<>();

    // --- CONSTRUCTORS ---

    public Vendor() {}

    public Vendor(Long id, String vendorName, String email, String phone, String industry, LocalDateTime createdAt, Set<DocumentType> supportedDocumentTypes) {
        this.id = id;
        this.vendorName = vendorName;
        this.email = email;
        this.phone = phone;
        this.industry = industry;
        this.createdAt = createdAt;
        this.supportedDocumentTypes = supportedDocumentTypes;
    }

    @PrePersist
    public void prePersist() { 
        this.createdAt = LocalDateTime.now(); 
    }

    // --- GETTERS AND SETTERS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Set<DocumentType> getSupportedDocumentTypes() { return supportedDocumentTypes; }
    public void setSupportedDocumentTypes(Set<DocumentType> supportedDocumentTypes) { 
        this.supportedDocumentTypes = (supportedDocumentTypes != null) ? supportedDocumentTypes : new HashSet<>(); 
    }
}