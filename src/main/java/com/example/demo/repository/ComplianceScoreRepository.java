package com.example.demo.repository;

import com.example.demo.model.ComplianceScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ComplianceScoreRepository extends JpaRepository<ComplianceScore, Long> {
    // Note: Used as findByVendor_Id in tests to navigate the relationship
    Optional<ComplianceScore> findByVendorId(Long vendorId); // [cite: 215]
}