package com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.request;


import com.example.insuranceSystem.domain.insurance.repository.entity.Insurance;
import com.example.insuranceSystem.domain.insurance.repository.entity.InsuranceCondition;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.InsuranceStatus;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.KindOfInsurance;
import com.example.insuranceSystem.global.enumerations.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InsuranceSaveRequest {

    @NotNull(message = "입력하지 않은 정보가 있습니다. 다시 시도해 주세요.")
    private String insuranceName;
    @NotNull(message = "입력하지 않은 정보가 있습니다. 다시 시도해 주세요.")
    private int fee;
    @NotNull(message = "입력하지 않은 정보가 있습니다. 다시 시도해 주세요.")
    private int maxAge;
    @NotNull(message = "입력하지 않은 정보가 있습니다. 다시 시도해 주세요.")
    private int minAge;
    @NotNull(message = "입력하지 않은 정보가 있습니다. 다시 시도해 주세요.")
    private String smoke;
    @NotNull(message = "입력하지 않은 정보가 있습니다. 다시 시도해 주세요.")
    private String alcohol;
    @NotNull(message = "입력하지 않은 정보가 있습니다. 다시 시도해 주세요.")
    private String cancer;
    @NotNull(message = "보험 정보를 선택해주세요.")
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
