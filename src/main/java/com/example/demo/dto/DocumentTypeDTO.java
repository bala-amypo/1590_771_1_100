package com.example.demo.dto;

import lombok.Data;

@Data
public class DocumentTypeDTO {
    private Long id;
    private String typeName;
    private String description;
    private Boolean required;
    private Integer weight;
}