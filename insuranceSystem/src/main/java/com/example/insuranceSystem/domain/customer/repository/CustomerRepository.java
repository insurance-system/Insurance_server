package com.example.insuranceSystem.domain.customer.repository;

import com.example.insuranceSystem.domain.customer.repository.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
