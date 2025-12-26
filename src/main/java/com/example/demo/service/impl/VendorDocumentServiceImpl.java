package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VendorDocumentServiceImpl {

    private final VendorDocumentRepository vendorDocumentRepository;
    private final VendorRepository vendorRepository;
    private final DocumentTypeRepository documentTypeRepository;

    public VendorDocumentServiceImpl(
            VendorDocumentRepository vendorDocumentRepository,
            VendorRepository vendorRepository,
            DocumentTypeRepository documentTypeRepository) {

        this.vendorDocumentRepository = vendorDocumentRepository;
        this.vendorRepository = vendorRepository;
        this.documentTypeRepository = documentTypeRepository;
    }

    public VendorDocument uploadDocument(Long vendorId, Long typeId, VendorDocument document) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vendor not found"));

        DocumentType type = documentTypeRepository.findById(typeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("DocumentType not found"));

        if (document.getExpiryDate() != null &&
                document.getExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiry date cannot be in the past");
        }

        document.setVendor(vendor);
        document.setDocumentType(type);
        document.setIsValid(true);

        return vendorDocumentRepository.save(document);
    }

    public VendorDocument getDocument(Long id) {
        return vendorDocumentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("VendorDocument not found"));
    }

    public List<VendorDocument> getDocumentsForVendor(Long vendorId) {
        return vendorDocumentRepository.findByVendorId(vendorId);
    }
}
