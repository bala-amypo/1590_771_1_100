package com.example.demo.repository;

import com.example.demo.model.Vendor;
import com.example.demo.model.VendorDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface VendorDocumentRepository extends JpaRepository<VendorDocument, Long> {

    // Used by controller/service
    List<VendorDocument> findByVendorId(Long vendorId);

    // Used by compliance score service
    List<VendorDocument> findByVendor(Vendor vendor);

    // REQUIRED by tests
    @Query("SELECT vd FROM VendorDocument vd WHERE vd.expiryDate < :today")
    List<VendorDocument> findExpiredDocuments(LocalDate today);
}
