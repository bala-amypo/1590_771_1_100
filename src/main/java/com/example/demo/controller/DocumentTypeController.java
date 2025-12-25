package com.example.demo.controller;

import com.example.demo.model.DocumentType;
import com.example.demo.service.DocumentTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/document-types")
@SecurityRequirement(name = "bearerAuth")
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public DocumentType createDocumentType(@RequestBody DocumentType type) {
        return documentTypeService.createDocumentType(type);
    }

    @GetMapping
    public List<DocumentType> getAllDocumentTypes() {
        return documentTypeService.getAllDocumentTypes();
    }

    @GetMapping("/{id}")
    public DocumentType getDocumentTypeById(@PathVariable Long id) {
        return documentTypeService.getDocumentType(id);
    }
}