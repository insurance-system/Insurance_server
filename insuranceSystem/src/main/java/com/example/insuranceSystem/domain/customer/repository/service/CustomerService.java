package com.example.insuranceSystem.domain.customer.repository.service;

import com.example.insuranceSystem.domain.customer.repository.CustomerRepository;
import com.example.insuranceSystem.domain.customer.repository.HealthInformationRepository;
import com.example.insuranceSystem.domain.customer.repository.entity.Customer;
import com.example.insuranceSystem.domain.customer.repository.entity.HealthInformation;
import com.example.insuranceSystem.domain.customer.web.dto.request.JoinCustomerRequest;
import com.example.insuranceSystem.global.web.response.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final HealthInformationRepository healthInformationRepository;
    private final CustomerRepository customerRepository;

    public Header<?> joinCustomer(JoinCustomerRequest joinCustmerRequest) {
        HealthInformation healthInformation = healthInformationRepository.save(joinCustmerRequest.toHealthInformationEntity());
        customerRepository.save(joinCustmerRequest.toCustomerEntity(healthInformation));
        return Header.OK();
    }
}
