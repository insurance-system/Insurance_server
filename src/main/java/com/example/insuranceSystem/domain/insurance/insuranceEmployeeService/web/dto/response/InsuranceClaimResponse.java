package com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InsuranceClaimResponse {
    private Long insuranceClaimId;
    private String claimContent;
    private int claimCost;
    private String customerName;
    private String insuranceName;
    private String kindOfInsurance;
}
