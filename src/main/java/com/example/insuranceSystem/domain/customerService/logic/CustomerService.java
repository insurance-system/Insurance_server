package com.example.insuranceSystem.domain.customerService.logic;

import com.example.insuranceSystem.domain.customerService.repository.CustomerRepository;
import com.example.insuranceSystem.domain.customerService.repository.HealthInformationRepository;
import com.example.insuranceSystem.domain.customerService.repository.entity.HealthInformation;
import com.example.insuranceSystem.domain.customerService.web.dto.request.JoinCustomerRequest;
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
