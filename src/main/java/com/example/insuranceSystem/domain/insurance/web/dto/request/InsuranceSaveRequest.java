package com.example.insuranceSystem.domain.insurance.web.dto.request;


import com.example.insuranceSystem.domain.insurance.repository.entity.Insurance;
import com.example.insuranceSystem.domain.insurance.repository.entity.InsuranceCondition;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.InsuranceStatus;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.KindOfInsurance;
import com.example.insuranceSystem.global.enumerations.Grade;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InsuranceSaveRequest {
    @NotNull
    private String insuranceName;
    @NotNull
    private int fee;
    @NotNull
    private int maxAge;
    @NotNull
    private int minAge;
    @NotNull
    private String smoke;
    @NotNull
    private String alcohol;
    @NotNull
    private String cancer;
    @NotNull
    private String kindOfInsurance;

    public Insurance toEntityWith(InsuranceCondition insuranceCondition) {
        return Insurance.builder()
                .insuranceName(this.insuranceName)
                .fee(this.fee)
                .kindOfInsurance(KindOfInsurance.getKindOfInsuranceBy(this.kindOfInsurance))
                .insuranceStatus(InsuranceStatus.UNDER_EXAMINATION)
                .insuranceCondition(insuranceCondition).build();
    }

    public InsuranceCondition toInsuranceConditionEntity() {
        return InsuranceCondition.builder()
                .maxAge(this.maxAge)
                .minAge(this.minAge)
                .smoke(Grade.getGrade(this.smoke))
                .alcohol(Grade.getGrade(this.alcohol))
                .cancer(Grade.getGrade(this.cancer)).build();

    }
}
