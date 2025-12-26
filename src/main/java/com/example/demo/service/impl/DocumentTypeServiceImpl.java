package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DocumentType;
import com.example.demo.repository.DocumentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeServiceImpl {

    private final DocumentTypeRepository documentTypeRepository;

    public DocumentTypeServiceImpl(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public DocumentType createDocumentType(DocumentType type) {
        return documentTypeRepository.save(type);
    }

    public List<DocumentType> getAllDocumentTypes() {
        return documentTypeRepository.findAll();
    }

    public DocumentType getDocumentType(Long id) {
        return documentTypeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("DocumentType not found"));
    }
}
