package com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.logic;

import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.request.InsuranceSaveRequest;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.request.LectureRequest;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.CustomerInfoResponse;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.InsuranceResponse;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.LectureResponse;
import com.example.insuranceSystem.global.web.response.Header;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface InsuranceEmployeeService {
    Header<InsuranceResponse> create(InsuranceSaveRequest insuranceSaveRequest);
    Header<InsuranceResponse> getInsurance(Long id);
    Header<CustomerInfoResponse> getCustomerandJoinedInsurance(Long id);
    Header<List<LectureResponse>> getLectureList();
    Header<Void> uploadEducationLecture(LectureRequest lectureRequest, HttpServletRequest request);
}
