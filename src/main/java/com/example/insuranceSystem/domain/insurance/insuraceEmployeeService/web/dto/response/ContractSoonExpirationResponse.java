package com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response;

import com.example.insuranceSystem.domain.common.entity.Address;
import com.example.insuranceSystem.domain.contract.repository.entity.Contract;
import com.example.insuranceSystem.domain.customerService.repository.entity.HealthInformation;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.KindOfInsurance;
import com.example.insuranceSystem.global.enumerations.KindOfJob;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
public class ContractSoonExpirationResponse {
    private String customerName;
    private String phoneNum;
    private Address address;
    private KindOfJob kindOfJob;
    private HealthInformation healthInformation;

    private String insuranceName;
    private int fee;
    private KindOfInsurance kindOfInsurance;

    @Builder
    public ContractSoonExpirationResponse(String customerName, String phoneNum, Address address, KindOfJob kindOfJob, HealthInformation healthInformation, String insuranceName, int fee, KindOfInsurance kindOfInsurance) {
        this.customerName = customerName;
        this.phoneNum = phoneNum;
        this.address = address;
        this.kindOfJob = kindOfJob;
        this.healthInformation = healthInformation;
        this.insuranceName = insuranceName;
        this.fee = fee;
        this.kindOfInsurance = kindOfInsurance;
    }
}
