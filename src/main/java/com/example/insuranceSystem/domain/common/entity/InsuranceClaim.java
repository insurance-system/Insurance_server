package com.example.insuranceSystem.domain.common.entity;

import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import com.example.insuranceSystem.domain.insurance.repository.entity.Insurance;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
@Entity
@DynamicInsert
public class InsuranceClaim {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "insurance_claim_id")
    private Long id;

    private String claimContent;

    private int claimCost;

    @ColumnDefault("-1")
    private Integer evaluateCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="insurance_id")
    private Insurance insurance;

    @Builder
    public InsuranceClaim(String claimContent, int claimCost) {
        this.claimContent = claimContent;
        this.claimCost = claimCost;
    }

    public InsuranceClaim(InsuranceClaim insuranceClaim, Customer customer, Insurance insurance) {
        this.claimContent = insuranceClaim.getClaimContent();
        this.claimCost = insuranceClaim.getClaimCost();
        this.evaluateCost = -1;
        this.addCustomer(customer);
        this.addInsurance(insurance);
    }

    public void addCustomer(Customer customer){
        this.customer = customer;
        this.customer.addInsuranceClaim(this);
    }

    public void addInsurance(Insurance insurance){
        this.insurance = insurance;
        this.insurance.addInsuranceClaim(this);
    }
}
