package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.logic;

import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.request.*;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response.ConsultInfoResponse;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response.JoinInsuranceResponse;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response.PaymentResponse;
import com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.web.dto.request.LineUpConsultRequest;
import com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.web.dto.response.InsuranceResponse;
import com.example.insuranceSystem.global.web.response.Header;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface InsuranceCustomerService {
    Header<Void> lineUpCustomerConsult(LineUpConsultRequest lineUpConsultRequest, HttpServletRequest request);
    Header<List<InsuranceResponse>> getInsuranceListOf(String kindOfInsurance);
    Header<JoinInsuranceResponse> requestJoiningInsurance(JoinInsuranceRequest joinInsuranceRequest, HttpServletRequest request);
    Header<List<InsuranceResponse>> getJoinedInsurances(HttpServletRequest request);
    Header<List<ConsultInfoResponse>> getEndOfConsultList(HttpServletRequest request);
    Header<Void> evaluateSatisfaction(EvaluateSatisfactionRequest evaluateSatisfactionRequest, HttpServletRequest request);
    Header<Void> doPayment(PaymentRequest paymentRequest, HttpServletRequest request);
    Header<List<PaymentResponse>> getPaymentHistory(HttpServletRequest request);
    Header<Void> acceptIncidentHandling(IncidentRequest incidentRequest, HttpServletRequest request);
    Header<Void> claimInsurance(ClaimInsuranceRequest claimInsuranceRequest, HttpServletRequest request);
}
