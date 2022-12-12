package com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.web.dto.response;

import com.example.insuranceSystem.domain.common.repository.entity.Address;
import com.example.insuranceSystem.domain.customerService.repository.entity.HealthInformation;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.KindOfInsurance;
import com.example.insuranceSystem.global.enumerations.KindOfJob;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InsuranceInterestedResponse {

    private Long empCusId;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private Address address;
    private KindOfInsurance kindOfInsurance;
    private KindOfJob kindOfJob;
    private HealthInformation healthInformation;

    @Builder
    public InsuranceInterestedResponse(Long empCusId, String customerName, String customerPhone, String customerEmail, Address address, KindOfInsurance kindOfInsurance, KindOfJob kindOfJob, HealthInformation healthInformation) {
        this.empCusId = empCusId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.address = address;
        this.kindOfInsurance = kindOfInsurance;
        this.kindOfJob = kindOfJob;
        this.healthInformation = healthInformation;
    }
}
