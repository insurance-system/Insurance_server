package com.example.insuranceSystem.domain.insurance.repository.entity;

import com.example.insuranceSystem.domain.common.entity.DateBaseEntity;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.InsuranceStatus;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.KindOfInsurance;
import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@Getter
@Entity
public class Insurance extends DateBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "insurance_id")
    private Long insuranceId;
    private String insuranceName;
    private int fee;
    @Enumerated(EnumType.STRING)
    private KindOfInsurance kindOfInsurance;
    @Enumerated(EnumType.STRING)
    private InsuranceStatus insuranceStatus;

    @OneToOne
    @JoinColumn(name = "insurance_condition_id")
    private InsuranceCondition insuranceCondition;

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

}

