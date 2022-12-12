package com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.web.dto.response;

import com.example.insuranceSystem.domain.common.repository.entity.Address;
import com.example.insuranceSystem.domain.customerService.repository.entity.HealthInformation;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.InsuranceStatus;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.KindOfInsurance;
import com.example.insuranceSystem.global.enumerations.KindOfJob;
import lombok.Builder;
import lombok.Getter;


@Getter
public class ContractCustomerResponse {
    private String customerName;
    private String phoneNum;
    private InsuranceStatus insuranceStatus;
    private Address address;
    private KindOfJob kindOfJob;
    private HealthInformation healthInformation;

    private String insuranceName;
    private int fee;
    private KindOfInsurance kindOfInsurance;

    @Builder
    public ContractCustomerResponse(String customerName, String phoneNum, InsuranceStatus insuranceStatus, Address address, KindOfJob kindOfJob, HealthInformation healthInformation, String insuranceName, int fee, KindOfInsurance kindOfInsurance) {
        this.customerName = customerName;
        this.phoneNum = phoneNum;
        this.insuranceStatus = insuranceStatus;
        this.address = address;
        this.kindOfJob = kindOfJob;
        this.healthInformation = healthInformation;
        this.insuranceName = insuranceName;
        this.fee = fee;
        this.kindOfInsurance = kindOfInsurance;
    }
}
