package com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UwListResponse {
    private Long contractId;

    private String customerCancer;
    private String customerSmoke;
    private String customerAlcohol;

    private String insuranceName;
    private String kindOfInsurance;
    private Integer insuranceFee;
    private String insuranceCancer;
    private String insuranceSmoke;
    private String insuranceAlcohol;

}
