package com.example.insuranceSystem.domain.contract.repository.entity;

import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import com.example.insuranceSystem.domain.employeeService.repository.entity.Employee;
import com.example.insuranceSystem.domain.insurance.repository.entity.Insurance;
import com.example.insuranceSystem.global.enumerations.ContractStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Contract {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contract_id")
    private Long contractId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
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

    public void addCustomer(Customer customer){
        this.customer = customer;
        customer.addContract(this);
    }

    public void addChargeOfEmployee(Employee chargeOfEmployee){
        this.chargeOfEmployee = chargeOfEmployee;
    }

    public void addInsurance(Insurance insurance){
        this.insurance = insurance;
    }

    public void updateContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
    }

    public Contract(Customer customer, Insurance insurance) {
        this.addCustomer(customer);
        this.addInsurance(insurance);
        this.updateContractStatus(ContractStatus.PROGRESS_UW);
    }
}