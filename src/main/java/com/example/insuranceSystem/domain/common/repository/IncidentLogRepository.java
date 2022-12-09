package com.example.insuranceSystem.domain.common.repository;

import com.example.insuranceSystem.domain.common.entity.IncidentLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentLogRepository extends JpaRepository<IncidentLog, Long> {
}
