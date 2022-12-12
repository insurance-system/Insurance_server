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
    Header<InsuranceResponse> create(InsuranceSaveRequest insuranceSaveRequest, HttpServletRequest request);
    Header<InsuranceResponse> getInsurance(Long id);
    Header<CustomerInfoResponse> getCustomerandJoinedInsurance(Long id, HttpServletRequest request);
    Header<List<LectureResponse>> getLectureList(HttpServletRequest request);
    Header<Void> uploadEducationLecture(LectureRequest lectureRequest, HttpServletRequest request);
    Header<List<UwListResponse>> getUwList(HttpServletRequest request);
    Header<Void> startUw(StartUwRequest startUwRequest, HttpServletRequest request);
    Header<List<IncidentLogListResponse>> getIncidentLogList(HttpServletRequest request);
    Header<Void> manageIncidentLog(Long id, HttpServletRequest request);
    Header<List<InsuranceClaimResponse>> getInsuranceClaimList(HttpServletRequest request);
    Header<Void> evaluateReward(EvaluateRewardRequest evaluateRewardRequest, HttpServletRequest request);

    Header<List<ContractWaitingCustomerResponse>> getContractCustomer(HttpServletRequest request);

    Header<List<ContractCustomerResponse>> getNearExpireContractList(HttpServletRequest request);

    Header<List<ContractCustomerResponse>> notifyContractStatus(HttpServletRequest request);

    Header<List<ContractCustomerResponse>> printExpirationContract(HttpServletRequest request);

    Header<List<ContractCustomerResponse>> printNonPaymentContract(HttpServletRequest request);
}
