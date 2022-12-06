package com.example.insuranceSystem.domain.employeeService.repository;

import com.example.insuranceSystem.domain.employeeService.repository.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
