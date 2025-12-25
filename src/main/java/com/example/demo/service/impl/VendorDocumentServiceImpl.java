package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DocumentType;
import com.example.demo.model.Vendor;
import com.example.demo.model.VendorDocument;
import com.example.demo.repository.DocumentTypeRepository;
import com.example.demo.repository.VendorDocumentRepository;
import com.example.demo.repository.VendorRepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;


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

    public VendorDocument uploadDocument(Long vendorId,
                                         Long typeId,
                                         VendorDocument document) {

        if (document.getFileUrl() == null || document.getFileUrl().isEmpty()) {
            throw new IllegalArgumentException("File URL is required");
        }

        if (document.getExpiryDate() != null &&
                document.getExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiry date cannot be in the past");
        }

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vendor not found"));

        DocumentType type = documentTypeRepository.findById(typeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("DocumentType not found"));

        document.setVendor(vendor);
        document.setDocumentType(type);

        boolean valid = document.getExpiryDate() == null ||
                document.getExpiryDate().isAfter(LocalDate.now());
        document.setIsValid(valid);

        return vendorDocumentRepository.save(document);
    }

    public List<VendorDocument> getDocumentsForVendor(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vendor not found"));

        return vendorDocumentRepository.findByVendor(vendor);
    }

    public VendorDocument getDocument(Long id) {
        return vendorDocumentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("VendorDocument not found"));
    }
}
