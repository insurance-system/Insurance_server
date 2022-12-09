package com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.logic;

import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.request.EvaluateRewardRequest;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.request.InsuranceSaveRequest;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.request.LectureRequest;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.request.StartUwRequest;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.*;
import com.example.insuranceSystem.global.web.response.Header;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface InsuranceEmployeeService {
    Header<InsuranceResponse> create(InsuranceSaveRequest insuranceSaveRequest);
    Header<InsuranceResponse> getInsurance(Long id);
    Header<CustomerInfoResponse> getCustomerandJoinedInsurance(Long id);
    Header<List<LectureResponse>> getLectureList();
    Header<Void> uploadEducationLecture(LectureRequest lectureRequest, HttpServletRequest request);
    Header<List<UwListResponse>> getUwList(HttpServletRequest request);
    Header<Void> startUw(StartUwRequest startUwRequest);
    Header<List<IncidentLogListResponse>> getIncidentLogList(HttpServletRequest request);
    Header<Void> manageIncidentLog(Long id, HttpServletRequest request);
    Header<List<InsuranceClaimResponse>> getInsuranceClaimList(HttpServletRequest request);
    Header<Void> evaluateReward(EvaluateRewardRequest evaluateRewardRequest);
}
