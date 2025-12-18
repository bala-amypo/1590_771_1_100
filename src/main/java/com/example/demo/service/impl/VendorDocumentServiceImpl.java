// package com.example.demo.service.impl;

// import com.example.demo.model.Vendor;
// import com.example.demo.model.DocumentType;
// import com.example.demo.model.VendorDocument;
// import com.example.demo.repository.VendorRepository;
// import com.example.demo.repository.DocumentTypeRepository;
// import com.example.demo.repository.VendorDocumentRepository;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.service.VendorDocumentService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.LocalDate;
// import java.util.List;

// @Service
// public class VendorDocumentServiceImpl implements VendorDocumentService {

//     @Autowired
//     private VendorRepository vendorRepository;

//     @Autowired
//     private DocumentTypeRepository documentTypeRepository;

//     @Autowired
//     private VendorDocumentRepository vendorDocumentRepository;

//     @Override
//     public VendorDocument uploadDocument(Long vendorId, Long typeId, VendorDocument document) {
//         Vendor vendor = vendorRepository.findById(vendorId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with ID: " + vendorId));

//         DocumentType type = documentTypeRepository.findById(typeId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Document type not found with ID: " + typeId));

//         if (document.getExpiryDate() != null && document.getExpiryDate().isBefore(LocalDate.now())) {
//             throw new IllegalArgumentException("Expiry date must be in the future");
//         }

//         document.setVendor(vendor);
//         document.setDocumentType(type);
//         document.setIsValid(true);

//         return vendorDocumentRepository.save(document);
//     }

//     @Override
//     public List<VendorDocument> getDocumentsForVendor(Long vendorId) {
//         return vendorDocumentRepository.findByVendorId(vendorId);
//     }

//     @Override
//     public VendorDocument getDocument(Long id) {
//         return vendorDocumentRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Document not found with ID: " + id));
//     }
// }
