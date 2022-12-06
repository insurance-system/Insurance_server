package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JoinInsuranceResponse {
    private String insuranceName;
    private String kindOfInsurance;
    private String contractStatus;

    public static JoinInsuranceResponse toDto(String insuranceName, String kindOfInsurance, String contractStatus) {
        JoinInsuranceResponse joinInsuranceResponse = new JoinInsuranceResponse();
        joinInsuranceResponse.insuranceName = insuranceName;
        joinInsuranceResponse.kindOfInsurance = kindOfInsurance;
        joinInsuranceResponse.contractStatus = contractStatus;
        return joinInsuranceResponse;
    }
}
