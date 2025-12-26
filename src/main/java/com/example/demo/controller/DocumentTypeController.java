package com.example.demo.controller;

import com.example.demo.model.DocumentType;
import com.example.demo.service.impl.DocumentTypeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/document-types")
public class DocumentTypeController {

    private final DocumentTypeServiceImpl documentTypeService;

    public DocumentTypeController(DocumentTypeServiceImpl documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @PostMapping
    public ResponseEntity<DocumentType> create(@RequestBody DocumentType type) {
        return ResponseEntity.ok(documentTypeService.createDocumentType(type));
    }

    @GetMapping
    public ResponseEntity<List<DocumentType>> getAll() {
        return ResponseEntity.ok(documentTypeService.getAllDocumentTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentType> getById(@PathVariable Long id) {
        return ResponseEntity.ok(documentTypeService.getDocumentType(id));
    }
}
