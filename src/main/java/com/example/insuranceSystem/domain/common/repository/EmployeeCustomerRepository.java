package com.example.insuranceSystem.domain.common.repository;

import com.example.insuranceSystem.domain.common.entity.EmployeeCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeCustomerRepository extends JpaRepository<EmployeeCustomer, Long> {
}
