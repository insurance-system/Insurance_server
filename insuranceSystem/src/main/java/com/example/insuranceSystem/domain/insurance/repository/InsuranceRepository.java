package com.example.insuranceSystem.domain.insurance.repository;

import com.example.insuranceSystem.domain.insurance.repository.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}
