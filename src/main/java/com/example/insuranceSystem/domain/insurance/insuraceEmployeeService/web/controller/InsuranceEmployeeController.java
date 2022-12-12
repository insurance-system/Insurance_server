package com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.controller;

import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.logic.InsuranceEmployeeService;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.request.EvaluateRewardRequest;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.request.InsuranceSaveRequest;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.request.LectureRequest;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.request.StartUwRequest;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.*;
import com.example.insuranceSystem.global.web.response.Header;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("insurance-employee")
@RestController
public class InsuranceEmployeeController {

    private final InsuranceEmployeeService insuranceService;

    @Operation(summary = "보험 정보 불러오기(완)", description = "보험 정보 불러오기")
    @ApiImplicitParams({@ApiImplicitParam(name = "user_id", value = "유저 ID", required = true, dataType = "Long", paramType = "header")})
    @GetMapping("/")
    public Header<InsuranceResponse> getInsurance(Long id){
        return insuranceService.getInsurance(id);
    }

    //상품개발팀
    @Operation(summary = "보험 생성하기(완)", description = "보험 생성하기")
    @PostMapping("/development")
    public Header<InsuranceResponse> developInsurance(@RequestBody @Valid InsuranceSaveRequest insuranceSaveRequest, HttpServletRequest request){
        return insuranceService.create(insuranceSaveRequest, request);
    }

    //영업팀
    /*1-2*/
    @Operation(summary = "상담대기 신규고객 명단 조회(완)", description = "상담 대기 신규 고객 명단 조회")
    @GetMapping("/sales/new-customer")
    public Header<List<ContractWaitingCustomerResponse>> doSalesEmployeeService(HttpServletRequest request){
        return insuranceService.getContractCustomer(request);
    }

   //영업팀
    //TODO 보험 관심자는 보험 상담 요청에 해당하는건가?
    @Operation(summary = "보험 관심자 배정()", description = "보험 관심자 배정")
    @PostMapping("/sales/interest")    // insurance-employee/sales
    public Header<InsuranceInterestedResponse> assignInsuranceInterested(HttpServletRequest request, @PathVariable("empCusId") Long empCusId){
        return insuranceService.assignInsuranceInterested(request, empCusId);
    }

    //사후관리팀
    @ApiOperation(value = "계약 기간 만료 임박 리스트 출력(완)", notes = "계약 기간 만료 임박 리스트 출력")
    @GetMapping("/contract/soon-expiration") // 계약 기간 만료 임박
    public Header<List<ContractCustomerResponse>> getNearExpireContractList(HttpServletRequest request){
        return insuranceService.getNearExpireContractList(request);
    }

    //사후관리팀
    @ApiOperation(value = "보험 납부 기간 만료 임박 조회(완)", notes = "보험 납부 기간 만료 임박")
    @GetMapping("/contract/payment-soon-expiration") //보험 납부 기간 만료 임박
    public Header<List<ContractCustomerResponse>> notifyContractStatus(HttpServletRequest request){
        return insuranceService.notifyContractStatus(request);
    }

    //계약관리팀
    @ApiOperation(value = "보험 만기 고객 조회",notes = "보험 만기 고객 조회//건강정보 테이블에 고객 ID 들어가야함")
    @GetMapping("/followup/expiration")
    public Header<List<ContractCustomerResponse>> printExpirationContract(HttpServletRequest request){
        return insuranceService.printExpirationContract(request);
    }

    //계약관리팀
    @ApiOperation(value = "미납 고객 조회", notes = "미납 고객 조회")
    @GetMapping("/contract/non-payment")
    public Header<List<ContractCustomerResponse>> printNonPaymentContract(HttpServletRequest request){
        return insuranceService.printNonPaymentContract(request);
    }

    // 영업교육팀
    @Operation(summary = "영업 교육 강의 리스트 출력", description = "영업 교육 강의 리스트 전체를 출력한다.")
    @GetMapping("/education")
    public Header<List<LectureResponse>> findLectureList(HttpServletRequest request){
        return insuranceService.getLectureList(request);
    }

    // 영업교육팀
    @Operation(summary = "영업 교육 강의 업로드", description = "영업 교육 강의를 업로드한다. header필요")
    @PostMapping("/education")
    public Header<Void> uploadEducationLecture(@RequestBody @Valid LectureRequest lectureRequest,
                                               HttpServletRequest request){
        return insuranceService.uploadEducationLecture(lectureRequest, request);
    }

    /*1-5*/
    // UW팀
    @Operation(summary = "인수심사 리스트 출력", description = "인수심사를 해야 하는 계약 리스트를 출력한다. 계약 리스트가 없다면 예외처리!")
    @GetMapping("/uw")
    public Header<List<UwListResponse>> getUwList(HttpServletRequest request){
        return insuranceService.getUwList(request);
    }

    //UW팀
    /*1-6*/
    @Operation(summary = "인수심사 수행", description = "계약 리스트로 정보를 확인하고 contractId와 & contractStatus:정상/인수심사 거절/인수심사 진행중(보류일 때) 중 하나를 선택해 인수심사를 수행한다.")
    @PatchMapping("/uw")
    public Header<Void> startUw(@RequestBody StartUwRequest startUwRequest, HttpServletRequest request){
        return insuranceService.startUw(startUwRequest, request);
    }

    // 고객정보팀
    @Operation(summary = "특정 id의 고객 및 가입된 보험 정보 출력", description = "특정 id의 고객 및 가입된 보험 정보 출력")
    @GetMapping("/customer/{customer_id}")
    public Header<CustomerInfoResponse> getCustomerandJoinedInsurance(@PathVariable ("customer_id") Long id, HttpServletRequest request){
        return insuranceService.getCustomerandJoinedInsurance(id, request);
    }

    // 손해접수팀
    @Operation(summary = "사고 접수 리스트 출력", description = "사고 접수된 리스트를 출력한다.")
    @GetMapping("/damage")
    public Header<List<IncidentLogListResponse>> manageIncidentLog(HttpServletRequest request){
        return insuranceService.getIncidentLogList(request);
    }

    //손해접수팀
    /*2-2*/
    @Operation(summary = "사고 접수 담당자 배정", description = "사고 접수된 리스트 중 하나를 선택해 담당자를 배정한다. header 필요")
    @PatchMapping("/damage/{incident_log_id}")
    public Header<Void> manageIncidentLog(@PathVariable ("incident_log_id") Long id, HttpServletRequest request){
        return insuranceService.manageIncidentLog(id, request);
    }

    // 보상평가팀
    @Operation(summary = "보상금 심사 리스트 출력", description = "보상금 심사가 필요한 리스트를 출력한다.")
    @GetMapping("/reward")
    public Header<List<InsuranceClaimResponse>> getInsuranceClaimList(HttpServletRequest request){
        return insuranceService.getInsuranceClaimList(request);
    }

    //보상ㅇ가팀
    @Operation(summary = "보상금 심사", description = "보상금 리스트 중 심사할 내역을 선택해 심사 비용을 등록한다.")
    @PatchMapping("/reward")
    public Header<Void> evaluateReward(@RequestBody EvaluateRewardRequest evaluateRewardRequest, HttpServletRequest request){
        return insuranceService.evaluateReward(evaluateRewardRequest, request);
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