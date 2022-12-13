package com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.web.dto.response;


import com.example.insuranceSystem.domain.insurance.repository.entity.Insurance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InsuranceResponse{
    private Long insuranceId;
    private String insuranceName;
    private int fee;
    private int maxAge;
    private int minAge;
    private String smoke;
    private String alcohol;
    private String cancer;
    private String kindOfInsurance;

    public static InsuranceResponse toDto(Insurance insurance){
        InsuranceResponse insuranceResponse = new InsuranceResponse();
        insuranceResponse.insuranceId = insurance.getInsuranceId();
        insuranceResponse.insuranceName = insurance.getInsuranceName();
        insuranceResponse.fee = insurance.getFee();
        insuranceResponse.maxAge = insurance.getInsuranceCondition().getMaxAge();
        insuranceResponse.minAge = insurance.getInsuranceCondition().getMinAge();
        insuranceResponse.smoke = insurance.getInsuranceCondition().getSmoke().getName();
        insuranceResponse.alcohol = insurance.getInsuranceCondition().getAlcohol().getName();
        insuranceResponse.cancer = insurance.getInsuranceCondition().getCancer().getName();
        return insuranceResponse;
    }
}
