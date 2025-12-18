// package com.example.demo.service.impl;

// import com.example.demo.model.ComplianceScore;
// import com.example.demo.model.DocumentType;
// import com.example.demo.model.VendorDocument;
// import com.example.demo.repository.ComplianceScoreRepository;
// import com.example.demo.repository.DocumentTypeRepository;
// import com.example.demo.repository.VendorDocumentRepository;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.service.ComplianceScoreService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Objects;

// @Service
// public class ComplianceScoreServiceImpl implements ComplianceScoreService {

//     @Autowired
//     private VendorDocumentRepository vendorDocumentRepository;

//     @Autowired
//     private DocumentTypeRepository documentTypeRepository;

//     @Autowired
//     private ComplianceScoreRepository complianceScoreRepository;

//     @Override
//     public ComplianceScore evaluateVendor(Long vendorId) {
//         List<DocumentType> requiredTypes = documentTypeRepository.findAll();
//         List<VendorDocument> uploadedDocs = vendorDocumentRepository.findByVendorId(vendorId);

//         double totalWeight = requiredTypes.stream().mapToDouble(DocumentType::getWeight).sum();
//         double score = 0;

//         for (DocumentType type : requiredTypes) {
//             boolean hasValidDoc = uploadedDocs.stream() .anyMatch(doc -> doc.getDocumentType() != null && Objects.equals(doc.getDocumentType().getId(), type.getId()) && Boolean.TRUE.equals(doc.getIsValid()));

//             if (hasValidDoc) {
//                 score += type.getWeight();
//             }
//         }

//         double finalScore = totalWeight == 0 ? 0 : (score / totalWeight) * 100;

//         ComplianceScore complianceScore = complianceScoreRepository.findByVendorId(vendorId)
//             .orElse(new ComplianceScore());
//         complianceScore.setId(vendorId);
//         complianceScore.setScoreValue(finalScore);

//         return complianceScoreRepository.save(complianceScore);
//     }

//     @Override
//     public ComplianceScore getScore(Long vendorId) {
//         return complianceScoreRepository.findByVendorId(vendorId)
//             .orElseThrow(() -> new ResourceNotFoundException("Compliance score not found for vendor ID: " + vendorId));
//     }

//     @Override
//     public List<ComplianceScore> getAllScores() {
//         return complianceScoreRepository.findAll();
//     }
// }
