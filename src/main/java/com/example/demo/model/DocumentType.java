package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "document_types",
        uniqueConstraints = @UniqueConstraint(columnNames = "typeName")
)
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String typeName;

    @Column
    private String description;

    @Column(nullable = false)
    private Boolean required;

    @Column(nullable = false)
    private Integer weight;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @PrePersist
    protected void onCreateAndValidate() {
        this.createdAt = LocalDateTime.now();
        validateWeight();
    }

    @PreUpdate
    protected void onUpdateValidate() {
        validateWeight();
    }

    private void validateWeight() {
        if (weight == null || weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }
    }

    public DocumentType(){}



    public DocumentType(String typeName, String description, Boolean required, Integer weight,
            LocalDateTime createdAt) {
        this.typeName = typeName;
        this.description = description;
        this.required = required;
        this.weight = weight;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
