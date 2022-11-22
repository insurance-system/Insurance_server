package com.example.insuranceSystem.domain.insurance.service;

import com.example.insuranceSystem.domain.insurance.web.dto.request.InsuranceSaveRequest;
import com.example.insuranceSystem.domain.insurance.web.dto.response.InsuranceResponse;
import com.example.insuranceSystem.global.web.response.Header;

import javax.servlet.http.HttpServletRequest;

public interface InsuranceService {
    Header<InsuranceResponse> create(InsuranceSaveRequest insuranceSaveRequest, HttpServletRequest request);
    Header<InsuranceResponse> findById(Long id, HttpServletRequest request);
}
