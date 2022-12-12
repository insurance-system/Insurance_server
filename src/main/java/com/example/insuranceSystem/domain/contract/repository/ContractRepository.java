package com.example.insuranceSystem.domain.contract.repository;

import com.example.insuranceSystem.domain.contract.repository.entity.Contract;
import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import com.example.insuranceSystem.global.enumerations.ContractStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByCustomerId(Long id);
    Optional<List<Contract>> findAllByCustomer(Customer customer);
    List<Contract> findAllByContractStatus(ContractStatus progressUw);
    List<Contract> findByExpiredDateBetween(LocalDateTime now,LocalDateTime month);
    List<Contract> findByPaymentDateBetween(LocalDateTime now,LocalDateTime week);
}
