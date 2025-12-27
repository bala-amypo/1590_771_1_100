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
    
    // Required by VendorDocumentServiceImpl line 49
    List<VendorDocument> findByVendorId(Long vendorId);

    // Required by Test 60 in the Test Suite
    List<VendorDocument> findByVendor(Vendor vendor);

    // Required by Test 57 for the Expiry Check
    @Query("SELECT d FROM VendorDocument d WHERE d.expiryDate < :date")
    List<VendorDocument> findExpiredDocuments(@Param("date") LocalDate date);
}