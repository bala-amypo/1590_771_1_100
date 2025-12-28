package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendor_documents")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorDocument {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Vendor vendor;
    
    @ManyToOne
    private DocumentType documentType;
    
    private String fileUrl;
    private LocalDateTime uploadedAt;
    private LocalDate expiryDate;
    private Boolean isValid;

    @PrePersist
    public void prePersist() {
        if(uploadedAt == null) uploadedAt = LocalDateTime.now();
        // Validation logic for isValid can also be here, but service usually handles business logic
    }
}