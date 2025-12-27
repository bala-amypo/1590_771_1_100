package com.example.demo.repository;

import com.example.demo.model.ComplianceScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ComplianceScoreRepository extends JpaRepository<ComplianceScore, Long> {
    // This underscore naming is a JPA convention for nested properties (Vendor's ID)
    // required specifically by the provided test suite
    Optional<ComplianceScore> findByVendor_Id(Long vendorId);
}