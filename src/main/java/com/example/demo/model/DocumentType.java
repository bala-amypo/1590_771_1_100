// package com.example.demo.model;

// import java.time.LocalDateTime;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "document_types")
// public class DocumentType {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)

//     private long id;
//     private String typeName;
//     private String description;
//     private Boolean required;
//     private Integer weight;
//     private LocalDateTime createdAt;

//     public DocumentType(){}

//     public DocumentType(String typeName, String description, Boolean required, Integer weight,
//             LocalDateTime createdAt) {
//         this.typeName = typeName;
//         this.description = description;
//         this.required = required;
//         this.weight = weight;
//         this.createdAt = createdAt;
//     }

//     public long getId() {
//         return id;
//     }

//     public void setId(long id) {
//         this.id = id;
//     }

//     public String getTypeName() {
//         return typeName;
//     }

//     public void setTypeName(String typeName) {
//         this.typeName = typeName;
//     }

//     public String getDescription() {
//         return description;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     public Boolean getRequired() {
//         return required;
//     }

//     public void setRequired(Boolean required) {
//         this.required = required;
//     }

//     public Integer getWeight() {
//         return weight;
//     }

//     public void setWeight(Integer weight) {
//         this.weight = weight;
//     }

//     public LocalDateTime getCreatedAt() {
//         return createdAt;
//     }

//     public void setCreatedAt(LocalDateTime createdAt) {
//         this.createdAt = createdAt;
//     }

    


    
// }
