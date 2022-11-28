package com.example.insuranceSystem.domain.customerService.repository;

import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
