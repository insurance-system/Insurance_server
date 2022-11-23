package com.example.insuranceSystem.domain.customer.repository;

import com.example.insuranceSystem.domain.customer.repository.entity.HealthInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthInformationRepository extends JpaRepository<HealthInformation, Long> {
}
