package com.example.insuranceSystem.domain.common.entity;

import com.example.insuranceSystem.domain.contract.repository.entity.Contract;
import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
@Entity
public class Payment extends DateBaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private Long id;

    private int payCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="contract_id")
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="contract_id")
    private Customer customer;

    public Payment(int payCost, Contract contract, Customer customer) {
        this.payCost = payCost;
        addContract(contract);
        addCustomer(customer);
    }

    public void addContract(Contract contract){
        this.contract = contract;
        this.contract.addPayment(this);
    }

    public void addCustomer(Customer customer){
        this.customer = customer;
        this.customer.addPayment(this);
    }
}
