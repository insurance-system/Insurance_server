package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response;

import com.example.insuranceSystem.domain.common.repository.entity.Address;
import com.example.insuranceSystem.domain.customerService.repository.entity.HealthInformation;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.InsuranceStatus;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.KindOfInsurance;
import com.example.insuranceSystem.global.enumerations.KindOfJob;
import lombok.Builder;
import lombok.Getter;


@Getter
public class ContractCustomerEmployeeResponse {
    private String customerName;
    private String phoneNum;
    private Address address;
    private String empName;
    private String empPhoneNum;

    @Builder
    public ContractCustomerEmployeeResponse(String customerName, String phoneNum, Address address, String empName, String empPhoneNum) {
        this.customerName = customerName;
        this.phoneNum = phoneNum;
        this.address = address;
        this.empName = empName;
        this.empPhoneNum = empPhoneNum;
    }
}
