package com.example.insuranceSystem.domain.customerService.logic;

import com.example.insuranceSystem.domain.customerService.exception.UserEmailNotFoundException;
import com.example.insuranceSystem.domain.customerService.repository.CustomerRepository;
import com.example.insuranceSystem.domain.customerService.repository.HealthInformationRepository;
import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import com.example.insuranceSystem.domain.customerService.repository.entity.HealthInformation;
import com.example.insuranceSystem.domain.customerService.web.dto.request.JoinCustomerRequest;
import com.example.insuranceSystem.domain.customerService.web.dto.request.LoginCustomerRequest;
import com.example.insuranceSystem.domain.customerService.web.dto.response.CustomerResponse;
import com.example.insuranceSystem.global.web.response.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final HealthInformationRepository healthInformationRepository;
    private final CustomerRepository customerRepository;

    //고객 회원가입
    @Transactional
    public Header<?> joinCustomer(JoinCustomerRequest joinCustomerRequest) {
        HealthInformation healthInformation = healthInformationRepository.save(joinCustomerRequest.toHealthInformationEntity());
        customerRepository.save(joinCustomerRequest.toCustomerEntity(healthInformation));
        return Header.OK();
    }

    public Header<?> login(LoginCustomerRequest loginCustomerRequest){
        Customer customer = customerRepository.findByEmail(loginCustomerRequest.getEmail())
                .orElseThrow(() -> new UserEmailNotFoundException(loginCustomerRequest.getEmail()));
        return Header.OK(CustomerResponse.toDto(customer));
    }
}
