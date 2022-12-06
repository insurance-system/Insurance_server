package com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.controller;

import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.logic.InsuranceEmployeeService;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.request.InsuranceSaveRequest;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.CustomerInfoResponse;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.InsuranceResponse;
import com.example.insuranceSystem.global.exception.NeedMoreInformationException;
import com.example.insuranceSystem.global.web.response.Header;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("insurance-employee")
@RestController
public class InsuranceEmployeeController {

    private final InsuranceEmployeeService insuranceService;

    @ApiOperation(value = "보험 정보 불러오기", notes = "보험 정보 불러오기")
    @GetMapping("/")
    public Header<InsuranceResponse> getInsurance(@PathVariable Long id, HttpServletRequest request){
        return insuranceService.getInsurance(id, request);
    }

    @ApiOperation(value = "보험 생성하기", notes = "보험 생성하기")
    @PostMapping("/development")
    public Header<InsuranceResponse> developInsurance(@RequestBody @Valid InsuranceSaveRequest insuranceSaveRequest,
                                                      BindingResult bindingResult,
                                                      HttpServletRequest request){
        if(bindingResult.hasErrors()) throw new NeedMoreInformationException("보험을 만들기 위한 정보가 부족합니다.");
        return insuranceService.create(insuranceSaveRequest, request);
    }

    //TODO 영업활동팀
    @ApiOperation(value = "상담대기 신규고객 명단 조회", notes = "상담 대기 신규 고객 명단 조회")
    @GetMapping("/sales/new-customer")
    public Header<?> doSalesEmployeeService(){
        return null;
    }

    @ApiOperation(value = "보험 관심자 배정", notes = "보험 관심자 배정")
    @GetMapping("/sales/interest")    // insurance-employee/sales
    public Header<?> assignInsuranceInterested(){
        return null;
    }

    @ApiOperation(value = "계약 기간 만료 임박 리스트 출력", notes = "계약 기간 만료 임박 리스트 출력")
    @GetMapping("/contract/soon-expiration") // 계약 기간 만료 임박
    public Header<?> getNearExpireContractList(){
        return null;
    }

    @ApiOperation(value = "보험 납부 기간 만료 임박", notes = "보험 납부 기간 만료 임박")
    @GetMapping("/contract/payment-soon-expiration") //보험 납부 기간 만료 임박
    public Header<?> notifyContractStatus(){
        return null;
    }

    @ApiOperation(value = "보험 만기 고객 조회",notes = "보험 만기 고객 조회//건강정보 테이블에 고객 ID 들어가야함")
    @GetMapping("/followup/expiration")
    public Header<?> printExpirationContract(){
        return null;
    }

    @ApiOperation(value = "미납 고객 조회", notes = "미납 고객 조회")
    @GetMapping("/contract/non-payment")
    public Header<?> printDefaultContract(){
        return null;
    }

    // TODO 영업교육팀
    @ApiOperation(value = "강의 자료 리스트 출력", notes = "강의 자료 리스트 출력")
    @GetMapping("/education")
    public Header<?> findLectureList(){
        return null;
    }

    @ApiOperation(value = "영업 교육 강의 자료 업로드", notes = "영업 교육 강의 업로드")
    @PostMapping("/education")
    public Header<?> uploadEducationLecture(){
        return null;
    }

    // TODO UW팀
    @ApiOperation(value = "인수심사 수행", notes = "인수심사 수행")
    @PostMapping("/uw")
    public Header<?> startUW(){
        return null;
    }

    // TODO 고객정보팀
    @Operation(summary = "특정 id의 고객 및 가입된 보험 정보 출력", description = "특정 id의 고객 및 가입된 보험 정보 출력")
    @GetMapping("/customer/{customer_id}")
    public Header<CustomerInfoResponse> getCustomerandJoinedInsurance(@PathVariable ("customer_id") Long id){
        return insuranceService.getCustomerandJoinedInsurance(id);
    }

    // TODO 손해접수팀
    @ApiOperation(value = "사고 접수", notes = "사고 접수")
    @PostMapping("/damage")
    public Header<?> manageIncidentReport(){
        return null;
    }

    // TODO 보상평가팀
    @ApiOperation(value = "보상금을 심사하다", notes = "보상금을 심사하다")
    @PostMapping("/reward")
    public Header<?> evaluateReward(){
        return null;
    }
}
//
//사후관리팀(보험 정보 안내)
//영업교육팀(),
//uw팀(),
//상품개발팀(),
//고객정보팀(),
//손해접수팀(사고접수),
//보상평가팀(보상금을 심사한다.),