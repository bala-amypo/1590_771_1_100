package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.VendorDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorDocumentServiceImpl implements VendorDocumentService {
    private final VendorDocumentRepository vendorDocumentRepository;
    private final VendorRepository vendorRepository;
    private final DocumentTypeRepository documentTypeRepository;

    @Override
    public VendorDocument uploadDocument(Long vendorId, Long typeId, VendorDocument document) {
        Vendor v = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
        DocumentType dt = documentTypeRepository.findById(typeId)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found"));

        if (document.getExpiryDate() != null && document.getExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiry date cannot be in the past");
        }

        document.setVendor(v);
        document.setDocumentType(dt);
        // Default validation logic: valid if expiry is null or future
        document.setIsValid(true); 
        return vendorDocumentRepository.save(document);
    }

    @Override
    public List<VendorDocument> getDocumentsForVendor(Long vendorId) {
        return vendorDocumentRepository.findByVendorId(vendorId);
    }

    @Override
    public VendorDocument getDocument(Long id) {
        return vendorDocumentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VendorDocument not found"));
    }
}