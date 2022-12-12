package com.example.insuranceSystem.domain.common.repository;

import com.example.insuranceSystem.domain.common.repository.entity.EmployeeCustomer;
import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import com.example.insuranceSystem.domain.employeeService.repository.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeCustomerRepository extends JpaRepository<EmployeeCustomer, Long> {
    Optional<List<EmployeeCustomer>> findAllByCustomer(Customer customer);

    Optional<List<EmployeeCustomer>> findByEmployee(Employee employee);
}
