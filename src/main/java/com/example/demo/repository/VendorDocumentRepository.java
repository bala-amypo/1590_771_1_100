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
    List<VendorDocument> findByVendorId(Long vendorId); // [cite: 205]
    List<VendorDocument> findByVendor(Vendor vendor); // [cite: 207]
    
    @Query("SELECT d FROM VendorDocument d WHERE d.expiryDate < :cutoffDate") // 
    List<VendorDocument> findExpiredDocuments(@Param("cutoffDate") LocalDate cutoffDate);
}