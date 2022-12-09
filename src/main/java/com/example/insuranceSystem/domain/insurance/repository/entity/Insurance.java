package com.example.insuranceSystem.domain.insurance.repository.entity;

import com.example.insuranceSystem.domain.common.entity.DateBaseEntity;
import com.example.insuranceSystem.domain.common.entity.InsuranceClaim;
import com.example.insuranceSystem.domain.contract.repository.entity.Contract;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.InsuranceStatus;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.KindOfInsurance;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;


@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@Getter
@Entity
public class
Insurance extends DateBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "insurance_id")
    private Long insuranceId;
    private String insuranceName;
    private int fee;
    @Enumerated(EnumType.STRING)
    private KindOfInsurance kindOfInsurance;
    @Enumerated(EnumType.STRING)
    private InsuranceStatus insuranceStatus;

    @OneToOne @JoinColumn(name = "insurance_condition_id")
    private InsuranceCondition insuranceCondition;

    @OneToMany(mappedBy = "insurance", cascade = ALL)
    private List<Contract> contracts = new ArrayList<>();

    @OneToMany(mappedBy = "insurance", cascade = ALL)
    private List<InsuranceClaim> insuranceClaims = new ArrayList<>();

    public void addInsuranceCondition(InsuranceCondition insuranceCondition){
        this.insuranceCondition = insuranceCondition;
    }

    @Builder
    public Insurance(
            String insuranceName,
            int fee,
            KindOfInsurance kindOfInsurance,
            InsuranceStatus insuranceStatus,
            InsuranceCondition insuranceCondition) {
        this.insuranceName = insuranceName;
        this.fee = fee;
        this.kindOfInsurance = kindOfInsurance;
        this.insuranceStatus = insuranceStatus;
        this.addInsuranceCondition(insuranceCondition);
        this.insuranceCondition.addInsurance(this);
    }

    public void addContract(Contract contract) {
        this.contracts.add(contract);
    }

    public void addInsuranceClaim(InsuranceClaim insuranceClaim) {
        this.insuranceClaims.add(insuranceClaim);
    }

}

