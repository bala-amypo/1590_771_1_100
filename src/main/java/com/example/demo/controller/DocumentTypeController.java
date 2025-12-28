package com.example.demo.controller;

import com.example.demo.model.DocumentType;
import com.example.demo.service.DocumentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/document-types")
@RequiredArgsConstructor
public class DocumentTypeController {
    private final DocumentTypeService documentTypeService;

    @PostMapping
    public DocumentType createDocumentType(@RequestBody DocumentType type) {
        return documentTypeService.createDocumentType(type);
    }

    @GetMapping
    public List<DocumentType> getAllDocumentTypes() {
        return documentTypeService.getAllDocumentTypes();
    }
    
    @GetMapping("/{id}")
    public DocumentType getDocumentType(@PathVariable Long id) {
        return documentTypeService.getDocumentType(id);
    }
}