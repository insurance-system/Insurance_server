package com.example.insuranceSystem.domain.insurance.web.controller;

import com.example.insuranceSystem.domain.insurance.repository.service.InsuranceService;
import com.example.insuranceSystem.domain.insurance.web.dto.request.InsuranceSaveRequest;
import com.example.insuranceSystem.domain.insurance.web.dto.response.InsuranceResponse;
import com.example.insuranceSystem.global.exception.NeedMoreInformationException;
import com.example.insuranceSystem.global.web.response.Header;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("insurance")
@RestController
public class InsuranceController {

    private final InsuranceService insuranceService;

    @PostMapping("/")
    public Header<InsuranceResponse> create(@RequestBody @Valid InsuranceSaveRequest insuranceSaveRequest,
                                            BindingResult bindingResult,
                                            HttpServletRequest request){
        if(bindingResult.hasErrors()) throw new NeedMoreInformationException("보험을 만들기 위한 정보가 부족합니다.");
        return insuranceService.create(insuranceSaveRequest, request);
    }

    @GetMapping("/{id}")
    public Header<InsuranceResponse> findById(@PathVariable Long id, HttpServletRequest request){
        return insuranceService.findById(id, request);
    }
}
