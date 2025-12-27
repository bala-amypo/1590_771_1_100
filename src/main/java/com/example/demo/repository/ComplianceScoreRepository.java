package com.example.demo.repository;

import com.example.demo.model.ComplianceScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ComplianceScoreRepository extends JpaRepository<ComplianceScore, Long> {
    // The test suite specifically calls this method name
    Optional<ComplianceScore> findByVendor_Id(Long vendorId); 
    
    // Also keep this for your internal service logic
    Optional<ComplianceScore> findByVendorId(Long vendorId); 
}