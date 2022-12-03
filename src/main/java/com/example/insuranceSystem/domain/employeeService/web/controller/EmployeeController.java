package com.example.insuranceSystem.domain.employeeService.web.controller;

import com.example.insuranceSystem.domain.employeeService.logic.EmployeeService;
import com.example.insuranceSystem.domain.employeeService.web.dto.request.LoginEmployeeRequest;
import com.example.insuranceSystem.global.web.response.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // 직원 로그인
    @PostMapping("/login")
    public Header<?> login(@RequestBody @Valid LoginEmployeeRequest loginEmployeeRequest){
        return employeeService.login(loginEmployeeRequest);
    }
}