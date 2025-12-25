package com.example.demo.controller;

import com.example.demo.model.VendorDocument;
import com.example.demo.service.VendorDocumentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-documents")
@SecurityRequirement(name = "bearerAuth")
public class VendorDocumentController {

    private final VendorDocumentService vendorDocumentService;

    public VendorDocumentController(VendorDocumentService vendorDocumentService) {
        this.vendorDocumentService = vendorDocumentService;
    }

    @PostMapping
    public VendorDocument uploadDocument(
            @RequestParam Long vendorId,
            @RequestParam Long typeId,
            @RequestBody VendorDocument document) {
        return vendorDocumentService.uploadDocument(vendorId, typeId, document);
    }

    @GetMapping("/vendor/{vendorId}")
    public List<VendorDocument> getVendorDocuments(@PathVariable Long vendorId) {
        return vendorDocumentService.getDocumentsForVendor(vendorId);
    }

    @GetMapping("/{id}")
    public VendorDocument getDocumentById(@PathVariable Long id) {
        return vendorDocumentService.getDocument(id);
    }
}