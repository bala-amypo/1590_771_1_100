package com.example.demo.repository;

import com.example.demo.model.ComplianceScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ComplianceScoreRepository extends JpaRepository<ComplianceScore, Long> {
    // Tests 60, 61, 62, and 64 specifically look for this naming convention
    Optional<ComplianceScore> findByVendor_Id(Long vendorId);
}