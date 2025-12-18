// package com.example.demo.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.model.VendorDocument;
// import com.example.demo.service.VendorDocumentService;

// import io.swagger.v3.oas.annotations.parameters.RequestBody;
// import io.swagger.v3.oas.annotations.tags.Tag;

// @RestController
// @RequestMapping("/api/vendor-documents")
// @Tag(name = "Vendor Document Controller")
// public class VendorDocumentController {

//     @Autowired
//     private VendorDocumentService vendorDocumentService;

//     @PostMapping("/{vendorId}/{typeId}")
//     public ResponseEntity<VendorDocument> uploadDocument(
//             @PathVariable Long vendorId,
//             @PathVariable Long typeId,
//             @RequestBody VendorDocument document) {
//         return ResponseEntity.ok(vendorDocumentService.uploadDocument(vendorId, typeId, document));
//     }

//     @GetMapping("/vendor/{vendorId}")
//     public ResponseEntity<List<VendorDocument>> getDocumentsForVendor(@PathVariable Long vendorId) {
//         return ResponseEntity.ok(vendorDocumentService.getDocumentsForVendor(vendorId));
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<VendorDocument> getDocument(@PathVariable Long id) {
//         return ResponseEntity.ok(vendorDocumentService.getDocument(id));
//     }
// }

