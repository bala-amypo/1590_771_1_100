package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DocumentType;
import com.example.demo.repository.DocumentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentTypeServiceImpl {
    private final DocumentTypeRepository repo;

    public DocumentType createDocumentType(DocumentType dt) {
        if(repo.existsByTypeName(dt.getTypeName())) throw new IllegalArgumentException("Duplicate type name");
        return repo.save(dt);
    }
    
    public DocumentType getDocumentType(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("DocumentType not found"));
    }

    public List<DocumentType> getAllDocumentTypes() { return repo.findAll(); }
}