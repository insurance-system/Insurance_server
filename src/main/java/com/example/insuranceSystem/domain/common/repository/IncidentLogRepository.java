package com.example.insuranceSystem.domain.common.repository;

import com.example.insuranceSystem.domain.common.entity.IncidentLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentLogRepository extends JpaRepository<IncidentLog, Long> {
    List<IncidentLog> findAllByEmployeeNull();
}
