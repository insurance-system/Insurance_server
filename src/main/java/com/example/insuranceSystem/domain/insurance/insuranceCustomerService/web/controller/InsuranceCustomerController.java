package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.controller;

import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.InsuranceResponse;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response.PaymentResponse;
import com.example.insuranceSystem.global.web.response.Header;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("insurance-customer")
@RestController
public class InsuranceCustomerController {

    @ApiOperation(value = "보험 가입하기", notes = "보험 가입하기")
    @PostMapping("/insurance")//   insurance-customer/insurance POST
    public Header<InsuranceResponse> joinInsurance() {
        return null;
    }

    @ApiOperation(value = "가입된 보험 리스트 출력", notes = "가입된 보험 리스트 출력")
    @GetMapping("/insurance")//   insurance-customer/insurance GET
    public Header<List<InsuranceResponse>> getJoinedInsurances() {
        return null;
    }

    @ApiOperation(value = "상담사 연결 요청", notes = "상담사 연결 요청")
    @GetMapping("/consult")//   insurance-customer/consult GET
    public Header<?> connectSalesEmployee(){
        return null;
    }

    @ApiOperation(value = "상담사 상담 과정 평가하기", notes = "상담사 상담 과정 평가하기")
    @PostMapping("/consult/{consult_id}/satisfaction")//   insurance-customer/consult/{consult_id}/satisfaction POST (score)
    public Header<?> evaluateSatisfaction(){
        return null;
    }

    @ApiOperation(value = "보험급 납부내역", notes = "보험급 납부내역")
    @GetMapping("/payment-history")//   insurance-customer/payment-history GET
    public Header<List<PaymentResponse>> printPaymentHistory(){
        return null;
    }

    @ApiOperation(value = "사고 처리 접수", notes = "사고 처리 접수")
    @GetMapping("/incident-accept")//   insurance-customer/incident-accept POST (사고 정보)
    public Header<List<PaymentResponse>> acceptIncidentHandling(){
        return null;
    }

    @ApiOperation(value = "보험금 청구하기", notes = "보험금 청구하기")
    @GetMapping("/insurance-claim")//   insurance-customer/insurance-claim POST (청구내역)
    public Header<?> claimInsurance(){
        return null;
    }
}
