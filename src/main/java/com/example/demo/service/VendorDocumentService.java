package com.example.demo.service;

import com.example.demo.model.VendorDocument;
import java.util.List;

public interface VendorDocumentService {

    VendorDocument uploadDocument(Long vendorId, Long documentTypeId, VendorDocument document);

    List<VendorDocument> getDocumentsForVendor(Long vendorId);

    VendorDocument getDocument(Long documentId);
}
