package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DocumentType;
import com.example.demo.model.Vendor;
import com.example.demo.model.VendorDocument;
import com.example.demo.repository.DocumentTypeRepository;
import com.example.demo.repository.VendorDocumentRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorDocumentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class VendorDocumentServiceImpl implements VendorDocumentService {

    private final VendorDocumentRepository vendorDocumentRepository;
    private final VendorRepository vendorRepository;
    private final DocumentTypeRepository documentTypeRepository;

    public VendorDocumentServiceImpl(VendorDocumentRepository vendorDocumentRepository,
                                     VendorRepository vendorRepository,
                                     DocumentTypeRepository documentTypeRepository) {
        this.vendorDocumentRepository = vendorDocumentRepository;
        this.vendorRepository = vendorRepository;
        this.documentTypeRepository = documentTypeRepository;
    }

    @Override
    @Transactional
    public VendorDocument uploadDocument(Long vendorId, Long documentTypeId, VendorDocument doc) {
        // Find Vendor
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with ID: " + vendorId));

        // Find DocumentType
        DocumentType docType = documentTypeRepository.findById(documentTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("DocumentType not found with ID: " + documentTypeId));

        // Test 14: Validation for Expiry Date
        if (doc.getExpiryDate() != null && doc.getExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiry date cannot be in the past");
        }

        // Set relationships for Test 13
        doc.setVendor(vendor);
        doc.setDocumentType(docType);

        return vendorDocumentRepository.save(doc);
    }

    @Override
    public VendorDocument getDocument(Long id) {
        // Test 16: Handle Not Found
        return vendorDocumentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VendorDocument not found with ID: " + id));
    }

    @Override
    public List<VendorDocument> getDocumentsByVendor(Long vendorId) {
        // Resolves your compilation error
        return vendorDocumentRepository.findByVendorId(vendorId);
    }

    @Override
    public List<VendorDocument> getAllDocuments() {
        return vendorDocumentRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteDocument(Long id) {
        if (!vendorDocumentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Document not found.");
        }
        vendorDocumentRepository.deleteById(id);
    }
}