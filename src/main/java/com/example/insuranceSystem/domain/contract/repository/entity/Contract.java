package com.example.insuranceSystem.domain.contract.repository.entity;

import com.example.insuranceSystem.domain.customer.repository.entity.Customer;
import com.example.insuranceSystem.domain.employee.repository.entity.Employee;
import com.example.insuranceSystem.domain.insurance.repository.entity.Insurance;
import com.example.insuranceSystem.global.enumerations.ContractStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class Contract {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contract_id")
    private Long contractId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Employee chargeOfEmployee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="insurance_id")
    private Insurance insurance;

    private LocalDateTime expiredDate;
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus;
}