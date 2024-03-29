package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.controller;

import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response.ContractCustomerEmployeeResponse;
import com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.web.dto.request.LineUpConsultRequest;
import com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.web.dto.response.InsuranceResponse;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.logic.InsuranceCustomerServiceImpl;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.request.*;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response.ConsultInfoResponse;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response.JoinInsuranceResponse;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response.PaymentResponse;
import com.example.insuranceSystem.global.web.response.Header;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("insurance-customer")
@RestController
public class InsuranceCustomerController {

    private final InsuranceCustomerServiceImpl insuranceCustomerServiceImpl;

    /*1-1*/
    @Operation(summary = "상담 대기 요청", description = "<완> 상담 대기 요청")
    @PostMapping("/consults")
    public Header<Void> lineUpCustomerConsult(@RequestBody LineUpConsultRequest lineUpConsultRequest, HttpServletRequest request){
        return insuranceCustomerServiceImpl.lineUpCustomerConsult(lineUpConsultRequest, request);
    }

    @Operation(summary = "상담 내용 리스트", description = "<완> 상담 내용 리스트를 보여준다. 이는 상담 내용을 평가하기 위해 필요하다.")
    @GetMapping("/consults/satisfaction")
    public Header<List<ConsultInfoResponse>> getEndOfConsultList(HttpServletRequest request){
        return insuranceCustomerServiceImpl.getEndOfConsultList(request);
    }

    @Operation(summary = "상담사 상담 과정 평가하기", description = "<완> 상담사 상담 과정 평가하기")
    @PostMapping("/consult/satisfaction")
    public Header<Void> evaluateSatisfaction(@RequestBody EvaluateSatisfactionRequest evaluateSatisfactionRequest,
                                          HttpServletRequest request){
        return insuranceCustomerServiceImpl.evaluateSatisfaction(evaluateSatisfactionRequest, request);
    }

    /*1-4*/
    @Operation(summary = "고객이 보험 목록 조회", description = "<완> 고객이 보험 목록 조회하기(클라이언트가 보낸 kind_of_insurance에 따라 LIFE/NON_LIFE 보험을 보여준다.")
    @GetMapping("/insurance/{kind_of_insurance}")
    public Header<List<InsuranceResponse>> getInsuranceList(@PathVariable String kind_of_insurance){
        return insuranceCustomerServiceImpl.getInsuranceListOf(kind_of_insurance);
    }

    /*1-5*/
    @Operation(summary = "보험 가입 신청하기", description = "<완> 보험 가입 신청하기: 클라이언트는 가입을 하고자 하는 보험의 id를 보내준다. " +
            "그리고 서버는 Contract를 만들어 저장하는데, 이때 Contract의 상태는 PROGRESS_UW이다. ")
    @PostMapping("/insurance")
    public Header<JoinInsuranceResponse> requestJoiningInsurance(@RequestBody JoinInsuranceRequest joinInsuranceRequest,
                                                                 HttpServletRequest request) {
        return insuranceCustomerServiceImpl.requestJoiningInsurance(joinInsuranceRequest, request);
    }

    /*1-8*/
    @Operation(summary = "가입된 보험 리스트 출력", description = "<완> 가입된 보험 리스트를 출력한다. 가입된 보험이 없다면 예외날림")
    @GetMapping("/insurance")
    public Header<List<InsuranceResponse>> getJoinedInsurances(HttpServletRequest request) {
        return insuranceCustomerServiceImpl.getJoinedInsurances(request);
    }

    @Operation(summary = "보험급 납부하기", description = "<완> 보험급 납부하기")
    @PostMapping("/payment")
    public Header<Void> doPayment(@RequestBody PaymentRequest paymentRequest,
                                                   HttpServletRequest request){
        return insuranceCustomerServiceImpl.doPayment(paymentRequest, request);
    }

    @Operation(summary = "보험급 납부 내역", description = "<완> 보험급 납부 내역 조회하기")
    @GetMapping("/payment-history")
    public Header<List<PaymentResponse>> getPaymentHistory(HttpServletRequest request){
        return insuranceCustomerServiceImpl.getPaymentHistory(request);
    }

    /*2-1*/
    @Operation(summary = "사고 처리 접수", description = "<완> 사고 처리 접수")
    @PostMapping("/incident-accept")
    public Header<Void> acceptIncidentHandling(@RequestBody IncidentRequest incidentRequest, HttpServletRequest request){
        return insuranceCustomerServiceImpl.acceptIncidentHandling(incidentRequest, request);
    }

    @Operation(summary = "보험금 청구하기", description = "<완> 보험금 청구하기")
    @PostMapping("/insurance-claim")
    public Header<Void> claimInsurance(@RequestBody ClaimInsuranceRequest claimInsuranceRequest, HttpServletRequest request){
        return insuranceCustomerServiceImpl.claimInsurance(claimInsuranceRequest, request);
    }

    @Operation(summary = "사고처리 접수 완료 리스트", description = "사고처리 접수 완료 리스트를 출력한다.")
    @GetMapping("/damage/incident")
    public Header<List<ContractCustomerEmployeeResponse>> getCompleteIncident(HttpServletRequest request){
        return insuranceCustomerServiceImpl.getCompleteIncident(request);
    }
}
