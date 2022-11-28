package com.example.insuranceSystem.domain.customerService.web.controller;

import com.example.insuranceSystem.domain.customerService.logic.CustomerService;
import com.example.insuranceSystem.domain.customerService.web.dto.request.JoinCustomerRequest;
import com.example.insuranceSystem.global.web.response.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // 고객 회원가입
    @PostMapping("/")
    public Header<?> joinCustomer(@RequestBody @Valid JoinCustomerRequest joinCustmerRequest) {
        return customerService.joinCustomer(joinCustmerRequest);
    }

}
