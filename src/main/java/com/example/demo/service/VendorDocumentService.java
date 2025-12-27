package com.example.demo.service;

import com.example.demo.model.VendorDocument;

import java.util.List;

public interface VendorDocumentService {

    List<VendorDocument> getVendorDocuments(Long vendorId);

    VendorDocument getVendorDocumentById(Long id);

    VendorDocument saveVendorDocument(VendorDocument vendorDocument);
}
