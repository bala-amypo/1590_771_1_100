package com.example.demo.service;

import com.example.demo.model.VendorDocument;
import java.util.List;

public interface VendorDocumentService {
    VendorDocument uploadDocument(Long vendorId, Long documentTypeId, VendorDocument doc);
    VendorDocument getDocument(Long id);
    List<VendorDocument> getAllDocuments();
    void deleteDocument(Long id);
    // This must match the name in the error log
    List<VendorDocument> getDocumentsForVendor(Long vendorId); 
}