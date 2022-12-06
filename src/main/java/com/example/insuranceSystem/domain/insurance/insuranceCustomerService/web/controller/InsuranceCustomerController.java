package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.controller;

import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.InsuranceResponse;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.logic.InsuranceCustomerService;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.request.JoinInsuranceRequest;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response.ConsultInfoResponse;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response.JoinInsuranceResponse;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response.PaymentResponse;
import com.example.insuranceSystem.global.web.response.Header;
import io.swagger.annotations.ApiOperation;
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

    private final InsuranceCustomerService insuranceCustomerService;

    @ApiOperation(value = "상담 대기 요청", notes = "상담 대기 요청")
    @GetMapping("/consults")
    public Header<Void> lineUpCustomerConsult(HttpServletRequest request){
        return insuranceCustomerService.lineUpCustomerConsult(request);
    }

    @ApiOperation(value = "상담 내용 리스트", notes = "상담 내용 리스트를 보여준다. 이는 상담 내용을 평가하기 위해 필요하다.")
    @PostMapping("/consults/satisfaction")//   insurance-customer/consult/{consult_id}/satisfaction POST (score)
    public Header<List<ConsultInfoResponse>> getEndOfConsultList(HttpServletRequest request){
        return insuranceCustomerService.getEndOfConsultList(request);
    }

    @ApiOperation(value = "상담사 상담 과정 평가하기", notes = "상담사 상담 과정 평가하기")
    @PostMapping("/consult/{consult_id}/satisfaction")//   insurance-customer/consult/{consult_id}/satisfaction POST (score)
    public Header<?> evaluateSatisfaction(@PathVariable String consult_id){
        return null;
    }

    @ApiOperation(value = "고객이 보험 목록 조회", notes = "고객이 보험 목록 조회하기(클라이언트가 보낸 kind_of_insurance에 따라 LIFE/NON_LIFE 보험을 보여준다.")
    @GetMapping("/insurance/{kind_of_insurance}")//   insurance-customer/insurance GET
    public Header<List<InsuranceResponse>> getInsuranceList(@PathVariable String kind_of_insurance){
        return insuranceCustomerService.getInsuranceListOf(kind_of_insurance);
    }

    @ApiOperation(value = "보험 가입 신청하기", notes = "보험 가입 신청하기: 클라이언트는 가입을 하고자 하는 보험의 id를 보내준다. " +
            "그리고 서버는 Contract를 만들어 저장하는데, 이때 Contract의 상태는 PROGRESS_UW이다. ")
    @PostMapping("/insurance")//   insurance-customer/insurance POST
    public Header<JoinInsuranceResponse> requestJoiningInsurance(@RequestBody JoinInsuranceRequest joinInsuranceRequest,
                                                                 HttpServletRequest request) {
        return insuranceCustomerService.requestJoiningInsurance(joinInsuranceRequest, request);
    }

    @ApiOperation(value = "가입된 보험 리스트 출력", notes = "가입된 보험 리스트 출력")
    @GetMapping("/insurance")//   insurance-customer/insurance GET
    public Header<List<InsuranceResponse>> getJoinedInsurances(HttpServletRequest request) {
        return insuranceCustomerService.getJoinedInsurances(request);
    }



    @ApiOperation(value = "보험급 납부내역", notes = "보험급 납부내역")
    @GetMapping("/payment-history")//   insurance-customer/payment-history GET
    public Header<List<PaymentResponse>> getPaymentHistory(HttpServletRequest request){
        return insuranceCustomerService.getPaymentHistory(request);
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
