package com.example.insuranceSystem.domain.customerService.web.controller;

import com.example.insuranceSystem.domain.customerService.logic.CustomerService;
import com.example.insuranceSystem.domain.customerService.web.dto.request.JoinCustomerRequest;
import com.example.insuranceSystem.domain.customerService.web.dto.request.LoginCustomerRequest;
import com.example.insuranceSystem.global.web.response.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // 고객 회원가입
    @PostMapping("/join")
    public Header<?> joinCustomer(@RequestBody @Valid JoinCustomerRequest joinCustomerRequest) {
        return customerService.joinCustomer(joinCustomerRequest);
    }

    // 고객 로그인
    @PostMapping("/login")
    public Header<?> login(@RequestBody @Valid LoginCustomerRequest loginCustomerRequest){
        return customerService.login(loginCustomerRequest);
    }
}
