package com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.web.dto.response;


import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomerInfoResponse {
    private Long customerId;
    private String email;
    private String name;
    private String address;
    private String detailAddress;
    private String zipcode;
    private String phoneNumber;
    private String kindOfJob;
    private String kindOfInsurance;
    private String ssn;
    private String cancer;
    private String smoke;
    private String alcohol;
    private List<InsuranceResponse> insuranceList;

    public static CustomerInfoResponse create(Customer customer, List<InsuranceResponse> contractList) {
        CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse();
        customerInfoResponse.customerId = customer.getId();
        customerInfoResponse.name = customer.getEmail();
        customerInfoResponse.address = customer.getAddress().getAddress();
        customerInfoResponse.detailAddress = customer.getAddress().getDetailAddress();
        customerInfoResponse.zipcode = customer.getAddress().getZipcode();
        customerInfoResponse.phoneNumber = customer.getPhoneNumber();
        customerInfoResponse.kindOfJob = customer.getKindOfJob().getName();
        customerInfoResponse.kindOfInsurance = customer.getKindOfInsurance().getName();
        customerInfoResponse.cancer = customer.getHealthInformation().getCancer().getName();
        customerInfoResponse.smoke = customer.getHealthInformation().getSmoke().getName();
        customerInfoResponse.alcohol = customer.getHealthInformation().getAlcohol().getName();
        customerInfoResponse.insuranceList = contractList;
        return customerInfoResponse;
    }
}
