package com.example.insuranceSystem.domain.customerService.web.dto.response;

import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CustomerResponse {
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

    public static CustomerResponse toDto(Customer customer){
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.customerId = customer.getId();
        customerResponse.email = customer.getEmail();
        customerResponse.name = customer.getName();
        customerResponse.address = customer.getAddress().getAddress();
        customerResponse.detailAddress = customer.getAddress().getDetailAddress();
        customerResponse.zipcode = customer.getAddress().getZipcode();
        customerResponse.phoneNumber = customer.getPhoneNumber();
        customerResponse.kindOfJob = "etc";// TODO customer.getKindOfJob()
        customerResponse.kindOfInsurance = customer.getKindOfInsurance().getName();
        customerResponse.ssn = customer.getSsn();
        customerResponse.cancer = customer.getHealthInformation().getCancer().getName();
        customerResponse.smoke = customer.getHealthInformation().getSmoke().getName();
        customerResponse.alcohol = customer.getHealthInformation().getAlcohol().getName();
        return customerResponse;
    }
}
