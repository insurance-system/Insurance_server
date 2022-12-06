package com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.logic;

import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.request.InsuranceSaveRequest;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.CustomerInfoResponse;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.InsuranceResponse;
import com.example.insuranceSystem.global.web.response.Header;

import javax.servlet.http.HttpServletRequest;

public interface InsuranceEmployeeService {
    Header<InsuranceResponse> create(InsuranceSaveRequest insuranceSaveRequest, HttpServletRequest request);
    Header<InsuranceResponse> getInsurance(Long id, HttpServletRequest request);
    Header<CustomerInfoResponse> getCustomerandJoinedInsurance(Long id);
}
