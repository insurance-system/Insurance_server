package com.example.insuranceSystem.domain.customerService.repository;

import com.example.insuranceSystem.domain.customerService.repository.entity.HealthInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthInformationRepository extends JpaRepository<HealthInformation, Long> {
}
