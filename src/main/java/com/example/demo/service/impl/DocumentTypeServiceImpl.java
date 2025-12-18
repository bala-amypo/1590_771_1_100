// package com.example.demo.service.impl;

// import com.example.demo.model.DocumentType;
// import com.example.demo.repository.DocumentTypeRepository;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.service.DocumentTypeService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class DocumentTypeServiceImpl implements DocumentTypeService {

//     @Autowired
//     private DocumentTypeRepository documentTypeRepository;

//     @Override
//     public DocumentType createDocumentType(DocumentType type) {
//         if (documentTypeRepository.existsByName(type.getTypeName())) {
//             throw new IllegalArgumentException("Document type name must be unique");
//         }
//         return documentTypeRepository.save(type);
//     }

//     @Override
//     public List<DocumentType> getAllDocumentTypes() {
//         return documentTypeRepository.findAll();
//     }

//     @Override
//     public DocumentType getDocumentType(Long id) {
//         return documentTypeRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Document type not found with ID: " + id));
//     }
// }
