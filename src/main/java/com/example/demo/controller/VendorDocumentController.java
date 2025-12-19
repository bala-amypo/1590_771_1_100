package com.example.demo.controller;

import com.example.demo.model.VendorDocument;
import com.example.demo.service.VendorDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-documents")
public class VendorDocumentController {

    private final VendorDocumentService vendorDocumentService;

    @Autowired
    public VendorDocumentController(VendorDocumentService vendorDocumentService) {
        this.vendorDocumentService = vendorDocumentService;
    }

    @PostMapping("/{vendorId}/{typeId}")
    public ResponseEntity<VendorDocument> uploadDocument(
            @PathVariable Long vendorId,
            @PathVariable Long typeId,
            @RequestBody VendorDocument document) {
        VendorDocument saved = vendorDocumentService.uploadDocument(vendorId, typeId, document);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<VendorDocument>> getDocumentsForVendor(@PathVariable Long vendorId) {
        List<VendorDocument> documents = vendorDocumentService.getDocumentsForVendor(vendorId);
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDocument> getDocumentById(@PathVariable Long id) {
        VendorDocument document = vendorDocumentService.getDocument(id);
        return ResponseEntity.ok(document);
    }
}
