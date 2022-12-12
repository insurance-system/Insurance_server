package com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.web.dto.response;

import com.example.insuranceSystem.domain.common.entity.Address;
import com.example.insuranceSystem.domain.customerService.repository.entity.HealthInformation;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.KindOfInsurance;
import com.example.insuranceSystem.global.enumerations.KindOfJob;
import lombok.*;

@NoArgsConstructor
@Getter
public class ContractWaitingCustomerResponse {
    private String customerName;
    private String ssn;
    private KindOfJob job;
    private String phoneNum;
    private String email;
    private Address address;
    private KindOfInsurance kindOfInsurance;
    private KindOfJob kindOfJob;//TODO
    private HealthInformation healthInformation;

    @Builder
    public ContractWaitingCustomerResponse(String customerName, String ssn, KindOfJob job, String phoneNum, String email, Address address, KindOfInsurance kindOfInsurance, KindOfJob kindOfJob, HealthInformation healthInformation) {
        this.customerName = customerName;
        this.ssn = ssn;
        this.job = job;
        this.phoneNum = phoneNum;
        this.email = email;
        this.address = address;
        this.kindOfInsurance = kindOfInsurance;
        this.kindOfJob = kindOfJob;
        this.healthInformation = healthInformation;
    }
}
