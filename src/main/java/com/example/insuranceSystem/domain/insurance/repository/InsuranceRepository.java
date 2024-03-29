package com.example.insuranceSystem.domain.insurance.repository;

import com.example.insuranceSystem.domain.contract.repository.entity.Contract;
import com.example.insuranceSystem.domain.insurance.repository.entity.Insurance;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.KindOfInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.List;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    List<Insurance> findAllByKindOfInsurance(KindOfInsurance kindOfInsurance);
    List<Insurance> findAllByContractsIn(List<Contract> contracts);
}
