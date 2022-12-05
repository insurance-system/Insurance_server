package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.logic;

import com.example.insuranceSystem.domain.common.entity.EmployeeCustomer;
import com.example.insuranceSystem.domain.common.repository.EmployeeCustomerRepository;
import com.example.insuranceSystem.domain.customerService.repository.CustomerRepository;
import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.exception.CustomerNotFoundException;
import com.example.insuranceSystem.global.web.response.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class InsuranceCustomerService {

    private final Environment env;
    private final EmployeeCustomerRepository employeeCustomerRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public Header<Void> lineUpCustomerConsult(HttpServletRequest request) {
        Customer customer = customerRepository
                .findById(getUserId(request)).orElseThrow(() -> new CustomerNotFoundException(getUserId(request)));
        employeeCustomerRepository.save(new EmployeeCustomer(customer));
        return Header.OK();
    }

    public Long getUserId(HttpServletRequest request) {
        return Long.parseLong(request.getHeader(env.getProperty("header.userid")));
    }
}
