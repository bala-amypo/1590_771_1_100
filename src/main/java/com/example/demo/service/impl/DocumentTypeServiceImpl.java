package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.DocumentType;
import com.example.demo.repository.DocumentTypeRepository;
import com.example.demo.service.DocumentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentTypeServiceImpl implements DocumentTypeService {
    private final DocumentTypeRepository documentTypeRepository;

    @Override
    public DocumentType createDocumentType(DocumentType type) {
        if(documentTypeRepository.existsByTypeName(type.getTypeName())) {
            throw new ValidationException("Duplicate type name");
        }
        return documentTypeRepository.save(type);
    }

    @Override
    public DocumentType getDocumentType(Long id) {
        return documentTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DocumentType not found"));
    }

    @Override
    public List<DocumentType> getAllDocumentTypes() {
        return documentTypeRepository.findAll();
    }
}