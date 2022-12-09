package com.example.insuranceSystem.domain.common.repository;

import com.example.insuranceSystem.domain.common.entity.InsuranceClaim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceClaimRepository extends JpaRepository<InsuranceClaim, Long> {
}
