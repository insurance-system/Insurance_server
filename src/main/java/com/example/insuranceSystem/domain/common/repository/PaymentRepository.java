package com.example.insuranceSystem.domain.common.repository;

import com.example.insuranceSystem.domain.common.entity.Payment;
import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<List<Payment>> findAllByCustomer(Customer customer);
}
