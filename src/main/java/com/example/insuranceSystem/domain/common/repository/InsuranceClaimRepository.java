package com.example.insuranceSystem.domain.common.repository;

import com.example.insuranceSystem.domain.common.repository.entity.InsuranceClaim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceClaimRepository extends JpaRepository<InsuranceClaim, Long> {
    List<InsuranceClaim> findAllByEvaluateCost(int i);
}
