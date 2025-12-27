package com.example.demo.service;

import com.example.demo.model.DocumentType;
import java.util.List;

public interface DocumentTypeService {
    DocumentType createDocumentType(DocumentType type); [cite: 256]
    List<DocumentType> getAllDocumentTypes(); [cite: 258]
    DocumentType getDocumentType(Long id); [cite: 259]
}