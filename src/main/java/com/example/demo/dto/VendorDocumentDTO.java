package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class VendorDocumentDTO {
    private Long id;
    private Long vendorId;
    private Long documentTypeId;
    private String fileUrl;
    private LocalDate expiryDate;
}