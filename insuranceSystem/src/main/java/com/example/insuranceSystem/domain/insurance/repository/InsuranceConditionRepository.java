package com.example.insuranceSystem.domain.insurance.repository;

import com.example.insuranceSystem.domain.insurance.repository.entity.InsuranceCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceConditionRepository extends JpaRepository<InsuranceCondition, Long> {
}
