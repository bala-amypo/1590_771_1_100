package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "document_types")
@Getter 
@Setter 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String typeName;
    private String description;
    private Boolean required;
    private Integer weight;
    private LocalDateTime createdAt;
    
    @ManyToMany(mappedBy = "supportedDocumentTypes")
    
    @ToString.Exclude 
    private Set<Vendor> vendors = new HashSet<>();

    @PrePersist
    public void prePersist() {
        if(createdAt == null) createdAt = LocalDateTime.now();
        if(weight == null) weight = 0;
    }
}