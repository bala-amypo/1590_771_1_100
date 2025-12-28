package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorDocumentServiceImpl {
    private final VendorDocumentRepository docRepo;
    private final VendorRepository vendorRepo;
    private final DocumentTypeRepository typeRepo;

    public VendorDocument uploadDocument(Long vendorId, Long typeId, VendorDocument doc) {
        Vendor v = vendorRepo.findById(vendorId).orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
        DocumentType dt = typeRepo.findById(typeId).orElseThrow(() -> new ResourceNotFoundException("Type not found"));

        if (doc.getExpiryDate() != null && doc.getExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiry date cannot be in the past");
        }

        doc.setVendor(v);
        doc.setDocumentType(dt);
        doc.setIsValid(true); // Default valid logic
        return docRepo.save(doc);
    }

    public VendorDocument getDocument(Long id) {
        return docRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("VendorDocument not found"));
    }
    
    public List<VendorDocument> getDocumentsForVendor(Long vendorId) {
        return docRepo.findByVendorId(vendorId);
    }
}