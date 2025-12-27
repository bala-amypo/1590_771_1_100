package com.example.demo.service;

import com.example.demo.model.VendorDocument;
import java.util.List;

public interface VendorDocumentService {
    VendorDocument uploadDocument(Long vendorId, Long typeId, VendorDocument document); [cite: 266]
    List<VendorDocument> getDocumentsForVendor(Long vendorId); [cite: 268]
    VendorDocument getDocument(Long id); [cite: 269]
}