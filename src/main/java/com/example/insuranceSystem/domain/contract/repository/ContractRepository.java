package com.example.insuranceSystem.domain.contract.repository;

import com.example.insuranceSystem.domain.contract.repository.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByCustomer_id(Long id);
}
