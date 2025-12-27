package com.example.demo.repository;

import com.example.demo.model.Vendor;
import com.example.demo.model.VendorDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface VendorDocumentRepository extends JpaRepository<VendorDocument, Long> {
    // Test 57: Validates findExpiredDocuments
    List<VendorDocument> findExpiredDocuments(LocalDate date);
    
    // Test 60: Validates findByVendor
    List<VendorDocument> findByVendor(Vendor vendor);
}