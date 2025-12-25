package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ComplianceScoreDTO {
    private Long id;
    private Long vendorId;
    private Double scoreValue;
    private LocalDateTime lastEvaluated;
    private String rating;
}