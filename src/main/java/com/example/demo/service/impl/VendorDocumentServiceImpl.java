package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.VendorDocumentService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class VendorDocumentServiceImpl implements VendorDocumentService {
    private final VendorDocumentRepository vendorDocumentRepository;
    private final VendorRepository vendorRepository;
    private final DocumentTypeRepository documentTypeRepository;

    public VendorDocumentServiceImpl(VendorDocumentRepository vendorDocumentRepository,
                                     VendorRepository vendorRepository,
                                     DocumentTypeRepository documentTypeRepository) { // [cite: 52, 53]
        this.vendorDocumentRepository = vendorDocumentRepository;
        this.vendorRepository = vendorRepository;
        this.documentTypeRepository = documentTypeRepository;
    }

    @Override
    public VendorDocument uploadDocument(Long vendorId, Long typeId, VendorDocument document) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found")); // [cite: 271]
        DocumentType type = documentTypeRepository.findById(typeId)
                .orElseThrow(() -> new ResourceNotFoundException("Document type not found")); // [cite: 271]

        if (document.getFileUrl() == null || document.getFileUrl().isEmpty()) {
            throw new IllegalArgumentException("File URL mandatory"); // [cite: 273]
        }

        if (document.getExpiryDate() != null && document.getExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiry date cannot be in the past"); // [cite: 274]
        }

        document.setVendor(vendor);
        document.setDocumentType(type);
        document.setIsValid(document.getExpiryDate() == null || document.getExpiryDate().isAfter(LocalDate.now())); // [cite: 275]
        
        return vendorDocumentRepository.save(document);
    }

    @Override
    public List<VendorDocument> getDocumentsForVendor(Long vendorId) {
        return vendorDocumentRepository.findByVendorId(vendorId);
    }

    @Override
    public VendorDocument getDocument(Long id) {
        return vendorDocumentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VendorDocument not found")); // [cite: 220]
    }
}