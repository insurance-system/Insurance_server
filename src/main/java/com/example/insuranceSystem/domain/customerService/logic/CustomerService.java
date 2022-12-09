package com.example.insuranceSystem.domain.customerService.logic;

import com.example.insuranceSystem.domain.customerService.exception.UserEmailNotFoundException;
import com.example.insuranceSystem.domain.customerService.exception.execute.*;
import com.example.insuranceSystem.domain.customerService.repository.CustomerRepository;
import com.example.insuranceSystem.domain.customerService.repository.HealthInformationRepository;
import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import com.example.insuranceSystem.domain.customerService.repository.entity.HealthInformation;
import com.example.insuranceSystem.domain.customerService.web.dto.request.JoinCustomerRequest;
import com.example.insuranceSystem.domain.customerService.web.dto.request.LoginCustomerRequest;
import com.example.insuranceSystem.domain.customerService.web.dto.response.CustomerLoginResponse;
import com.example.insuranceSystem.domain.customerService.web.dto.response.CustomerResponse;
import com.example.insuranceSystem.global.web.response.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final HealthInformationRepository healthInformationRepository;
    private final CustomerRepository customerRepository;

    //고객 회원가입
    @Transactional
    public Header<?> joinCustomer(JoinCustomerRequest joinCustomerRequest) {
        checkValidation(joinCustomerRequest);
        checkEmailExist(joinCustomerRequest.getEmail());
        HealthInformation healthInformation = healthInformationRepository.save(joinCustomerRequest.toHealthInformationEntity());
        customerRepository.save(joinCustomerRequest.toCustomerEntity(healthInformation));
        return Header.OK();
    }

    public Header<?> login(LoginCustomerRequest loginCustomerRequest){
        Customer customer = customerRepository.findByEmail(loginCustomerRequest.getEmail())
                .orElseThrow(CustomerNotFoundException::new);
        if(!loginCustomerRequest.getPassword().equals(customer.getPassword())) throw new WrongPasswordException();
        return Header.OK(new CustomerLoginResponse(customer.getId(), customer.getKindOfRole().getName()));
    }

    private void checkValidation(JoinCustomerRequest joinCustomerRequest) {
        checkEmailValidation(joinCustomerRequest.getEmail());
        checkPasswordValidation(joinCustomerRequest.getPassword());
        checkPhoneValidation(joinCustomerRequest.getPhoneNumber());
        checkZipcodeValidation(joinCustomerRequest.getZipcode());
        checkSsnValidation(joinCustomerRequest.getSsn());
    }

    private void checkPasswordValidation(String password) {
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$";
        if(!Pattern.matches(passwordPattern, password)) throw new InvalidPasswordException();
    }

    private void checkZipcodeValidation(String zipcode) {
        String zipcodePattern = "^[0-9]*${5}";
        if(!Pattern.matches(zipcodePattern, zipcode)) throw new InvalidZipcodeException();
    }

    private void checkSsnValidation(String ssn) {
        String SsnPattern = "^\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|[3][01])-[1-4][0-9]{6}$";
        if(!Pattern.matches(SsnPattern, ssn)) throw new InvalidSsnException();
    }

    private void checkPhoneValidation(String phoneNumber) {
        String phonePattern = "^\\d{3}-\\d{3,4}-\\d{4}$";
        if(!Pattern.matches(phonePattern, phoneNumber)) throw new InvalidPhoneException();
    }

    private void checkEmailValidation(String email) {
        String EmailPattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
        if(!Pattern.matches(EmailPattern, email)) throw new InvalidEmailException();
    }

    private void checkEmailExist(String email) {
        if(customerRepository.existsByEmail(email)) throw new ExistCustomerException();
    }
}
