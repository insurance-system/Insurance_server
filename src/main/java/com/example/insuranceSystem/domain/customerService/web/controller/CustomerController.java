package com.example.insuranceSystem.domain.customerService.web.controller;

import com.example.insuranceSystem.domain.customerService.logic.CustomerService;
import com.example.insuranceSystem.domain.customerService.web.dto.request.JoinCustomerRequest;
import com.example.insuranceSystem.domain.customerService.web.dto.request.LoginCustomerRequest;
import com.example.insuranceSystem.global.web.response.Header;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    //고객 회원가입
    @Operation(summary = "고객 회원가입", description = "고객 회원가입하기 (email = 아이디, 비밀번호는 문자,숫자,특수문자 포함 8~16자)")
    @PostMapping("/join")
    public Header<?> joinCustomer(@RequestBody @Valid JoinCustomerRequest joinCustomerRequest) {
        return customerService.joinCustomer(joinCustomerRequest);
    }

    //고객 로그인
    @Operation(summary = "고객 로그인", description = "email, password / 존재하지 않는 이메일 및 불일치하는 비밀번호 예외처리")
    @PostMapping("/login")
    public Header<?> login(@RequestBody @Valid LoginCustomerRequest loginCustomerRequest){
        return customerService.login(loginCustomerRequest);
    }
}
