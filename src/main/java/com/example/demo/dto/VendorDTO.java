package com.example.demo.dto;

import lombok.Data;
import java.util.Set;

@Data
public class VendorDTO {
    private Long id;
    private String vendorName;
    private String email;
    private String phone;
    private String industry;
    private Set<Long> supportedDocumentTypeIds;
}