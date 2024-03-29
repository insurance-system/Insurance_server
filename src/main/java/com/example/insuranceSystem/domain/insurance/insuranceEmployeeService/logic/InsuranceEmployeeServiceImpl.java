package com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.logic;

import com.example.insuranceSystem.domain.common.repository.entity.EmployeeCustomer;
import com.example.insuranceSystem.domain.common.repository.entity.IncidentLog;
import com.example.insuranceSystem.domain.common.repository.entity.InsuranceClaim;
import com.example.insuranceSystem.domain.common.repository.EmployeeCustomerRepository;
import com.example.insuranceSystem.domain.common.repository.IncidentLogRepository;
import com.example.insuranceSystem.domain.common.repository.InsuranceClaimRepository;
import com.example.insuranceSystem.domain.contract.repository.ContractRepository;
import com.example.insuranceSystem.domain.contract.repository.entity.Contract;
import com.example.insuranceSystem.domain.customerService.exception.execute.CustomerNotFoundException;
import com.example.insuranceSystem.domain.customerService.repository.CustomerRepository;
import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import com.example.insuranceSystem.domain.employeeService.exception.execute.EmployeeNotFoundException;
import com.example.insuranceSystem.domain.employeeService.repository.EmployeeRepository;
import com.example.insuranceSystem.domain.employeeService.repository.LectureRepository;
import com.example.insuranceSystem.domain.employeeService.repository.entity.Department;
import com.example.insuranceSystem.domain.employeeService.repository.entity.Employee;
import com.example.insuranceSystem.domain.employeeService.repository.entity.Lecture;
import com.example.insuranceSystem.domain.insurance.exception.execute.*;
import com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.web.dto.request.EvaluateRewardRequest;
import com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.web.dto.request.InsuranceSaveRequest;
import com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.web.dto.request.LectureRequest;
import com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.web.dto.request.StartUwRequest;
import com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.web.dto.response.*;
import com.example.insuranceSystem.domain.insurance.repository.InsuranceRepository;
import com.example.insuranceSystem.domain.insurance.repository.entity.Insurance;
import com.example.insuranceSystem.domain.insurance.repository.entity.InsuranceCondition;
import com.example.insuranceSystem.global.enumerations.ContractStatus;
import com.example.insuranceSystem.global.enumerations.Grade;
import com.example.insuranceSystem.global.util.date.DateFormatter;
import com.example.insuranceSystem.global.web.response.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly=true)
@RequiredArgsConstructor
@Service
public class InsuranceEmployeeServiceImpl implements InsuranceEmployeeService {

    private final InsuranceRepository insuranceRepository;
    private final ContractRepository contractRepository;
    private final CustomerRepository customerRepository;
    private final LectureRepository lectureRepository;
    private final EmployeeRepository employeeRepository;
    private final IncidentLogRepository incidentLogRepository;
    private final InsuranceClaimRepository insuranceClaimRepository;
    private final EmployeeCustomerRepository employeeCustomerRepository;

    @Override
    public Header<InsuranceResponse> getInsurance(Long id) {
        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(InsuranceNotFoundException::new);
        return Header.OK(InsuranceResponse.toDto(insurance));
    }

    // 특정 id의 고객 및 가입된 보험 정보 출력
    @Override
    public Header<CustomerInfoResponse> getCustomerAndJoinedInsurance(Long id, HttpServletRequest request) {
        Employee employee = employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        validateRole(employee.getDepartment(),"고객정보팀");
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        List<Contract> contractList = contractRepository.findByCustomerId(id);
        List<InsuranceResponse> insuranceList = new ArrayList<>();
        for (Contract contract : contractList) {
            Insurance insurance = insuranceRepository.findById(contract.getInsurance().getInsuranceId()).orElseThrow(null);
            insuranceList.add(InsuranceResponse.toDto(insurance));
        }
        return Header.OK(CustomerInfoResponse.create(customer, insuranceList));
    }

    // 영업 교육 강의 리스트 출력
    @Override
    public Header<List<LectureResponse>> getLectureList(HttpServletRequest request) {
        Employee employee = employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        validateRole(employee.getDepartment(),"영업교육팀");
        List<Lecture> lectureList = lectureRepository.findAll();
        if(lectureList.isEmpty()) throw new NoLectureListException();
        return Header.OK(lectureList.stream()
                .map(l -> new LectureResponse(
                        l.getLectureName(),
                        l.getLectureUrl())
                ).collect(Collectors.toList()));
    }

    // 영업 교육 강의 업로드
    @Transactional
    @Override
    public Header<Void> uploadEducationLecture(LectureRequest lectureRequest, HttpServletRequest request) {
        Employee employee = employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        validateRole(employee.getDepartment(),"영업교육팀");
        lectureRepository.save(new Lecture(
                lectureRequest.getLectureName(),
                lectureRequest.getLectureUrl(),
                employee));
        return Header.OK();
    }

    // 인수심사 리스트 출력
    @Override
    public Header<List<UwListResponse>> getUwList(HttpServletRequest request) {
        Employee employee = employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        validateRole(employee.getDepartment(),"U/W팀");
        List<Contract> contracts = contractRepository.findAllByContractStatus(ContractStatus.PROGRESS_UW);
        if(contracts.isEmpty()) throw new NoContractToUwException();
        return Header.OK(contracts.stream()
                .map(c -> new UwListResponse(
                        c.getContractId(),
                        c.getCustomer().getHealthInformation().getCancer().getName(),
                        c.getCustomer().getHealthInformation().getSmoke().getName(),
                        c.getCustomer().getHealthInformation().getAlcohol().getName(),
                        c.getInsurance().getInsuranceName(),
                        c.getInsurance().getKindOfInsurance().getName(),
                        c.getInsurance().getFee(),
                        c.getInsurance().getInsuranceCondition().getCancer().getName(),
                        c.getInsurance().getInsuranceCondition().getSmoke().getName(),
                        c.getInsurance().getInsuranceCondition().getAlcohol().getName()))
                .collect(Collectors.toList()));
    }

    // 인수심사 수행
    @Transactional
    @Override
    public Header<Void> startUw(StartUwRequest startUwRequest, HttpServletRequest request) {
        Employee employee = employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        validateRole(employee.getDepartment(),"U/W팀");
        Contract contract = contractRepository.findById(startUwRequest.getContractId()).orElseThrow(NotFoundContractException::new);
        contract.setContractStatus(ContractStatus.getContractStatusByName(startUwRequest.getContractStatus()));
        contractRepository.save(contract);
        return Header.OK();
    }

    // 사고 접수 리스트
    @Override
    public Header<List<IncidentLogListResponse>> getIncidentLogList(HttpServletRequest request) {
        Employee employee = employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        validateRole(employee.getDepartment(),"손해접수팀");
        List<IncidentLog> incidentLogs = incidentLogRepository.findAllByEmployeeNull();
        if(incidentLogs.isEmpty()) throw new NoIncidentLogListException();
        return Header.OK(incidentLogs.stream()
                .map(il -> new IncidentLogListResponse(
                        il.getId(),
                        il.getCustomer().getName(),
                        il.getIncidentPhoneNumber(),
                        DateFormatter.dateToStr(il.getIncidentDate()),
                        il.getIncidentSite(),
                        il.getCarNumber(),
                        il.getIncidentCategory().getName()))
                .collect(Collectors.toList()));
    }

    // 사고 접수 담당자 배정
    @Transactional
    @Override
    public Header<Void> manageIncidentLog(Long id, HttpServletRequest request) {
        Employee employee = employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        validateRole(employee.getDepartment(),"손해접수팀");
        IncidentLog incidentLog = incidentLogRepository.findById(id).orElseThrow(NotFoundIncidentLogException::new);
        incidentLog.setEmployee(employee);
        incidentLogRepository.save(incidentLog);
        return Header.OK();
    }

    // 보험금 심사 리스트 출력
    @Override
    public Header<List<InsuranceClaimResponse>> getInsuranceClaimList(HttpServletRequest request) {
        Employee employee = employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        List<InsuranceClaim> insuranceClaims = insuranceClaimRepository.findAllByEvaluateCost(-1);
        if(insuranceClaims.isEmpty()) throw new NoInsuranceClaimToEvaluateException();
        return Header.OK(insuranceClaims.stream()
                .map(ic -> new InsuranceClaimResponse(
                        ic.getId(),
                        ic.getClaimContent(),
                        ic.getClaimCost(),
                        ic.getCustomer().getName(),
                        ic.getInsurance().getInsuranceName(),
                        ic.getInsurance().getKindOfInsurance().getName()))
                .collect(Collectors.toList()));
    }

    // 보상금 심사
    @Transactional
    @Override
    public Header<Void> evaluateReward(EvaluateRewardRequest evaluateRewardRequest, HttpServletRequest request) {
        Employee employee = employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        validateRole(employee.getDepartment(),"보상평가원");
        InsuranceClaim insuranceClaim = insuranceClaimRepository.findById(evaluateRewardRequest.getInsuranceClaimId()).orElseThrow(NotFoundInsuranceClaimException::new);
        insuranceClaim.setEvaluateCost(evaluateRewardRequest.getEvaluateFee());
        insuranceClaimRepository.save(insuranceClaim);
        return Header.OK();
    }

    @Override
    public Header<List<ContractWaitingCustomerResponse>> getContractCustomer(HttpServletRequest request){
        Employee employee = employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        validateRole(employee.getDepartment(),"영업활동팀");
        return Header.OK(employeeCustomerRepository.findByEmployee(null).get().stream()
                .map(cs -> ContractWaitingCustomerResponse.builder()
                        .empCusId(cs.getEmpCusId())
                        .address(cs.getCustomer().getAddress())
                        .customerName(cs.getCustomer().getName())
                        .email(cs.getCustomer().getEmail())
                        .healthInformation(cs.getCustomer().getHealthInformation())
                        .job(cs.getCustomer().getKindOfJob())
                        .kindOfInsurance(cs.getCustomer().getKindOfInsurance())
                        .ssn(cs.getCustomer().getSsn())
                        .phoneNum(cs.getCustomer().getPhoneNumber())
                        .build())
                .collect(Collectors.toList()));
    }

    @Override
    public Header<List<ContractCustomerResponse>> getNearExpireContractList(HttpServletRequest request) {
        Employee employee = employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        validateRole(employee.getDepartment(),"사후관리팀");
        employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        List<Contract> byExpiredDateAfter = contractRepository.findByExpiredDateBetween(LocalDateTime.now(),LocalDateTime.now().plusMonths(2));
        return getContractCustomerResponse(byExpiredDateAfter);
    }

    @Override
    public Header<List<ContractCustomerResponse>> notifyContractStatus(HttpServletRequest request) {
        Employee employee = employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        validateRole(employee.getDepartment(),"사후관리팀");
        employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        List<Contract> byExpiredDateAfter = contractRepository.findByPaymentDateBetween(LocalDateTime.now(),LocalDateTime.now().plusWeeks(1));
        return getContractCustomerResponse(byExpiredDateAfter);
    }

    @Override
    public Header<List<ContractCustomerResponse>> printExpirationContract(HttpServletRequest request) {
        Employee employee = employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        validateRole(employee.getDepartment(),"계약관리팀");
        employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        List<Contract> allByContractStatus = contractRepository.findAllByContractStatus(ContractStatus.EXPIRATION);
        return getContractCustomerResponse(allByContractStatus);
    }

    @Override
    public Header<List<ContractCustomerResponse>> printNonPaymentContract(HttpServletRequest request) {
        Employee employee = employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        validateRole(employee.getDepartment(),"계약관리팀");
        employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        List<Contract> allByContractStatus = contractRepository.findAllByContractStatus(ContractStatus.NON_PAYMENT);
        return getContractCustomerResponse(allByContractStatus);
    }

    @Transactional
    @Override
    public Header<InsuranceInterestedResponse> assignInsuranceInterested(HttpServletRequest request, Long insuranceNum) {
        Employee employee = employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        validateRole(employee.getDepartment(),"영업활동팀");
        EmployeeCustomer employeeCustomer = employeeCustomerRepository.findById(insuranceNum).get();
        employeeCustomer.addEmployee(employee);
        return Header.OK(InsuranceInterestedResponse.builder()
                .empCusId(employeeCustomer.getEmpCusId())
                .customerName(employeeCustomer.getCustomer().getName())
                .customerEmail(employeeCustomer.getCustomer().getEmail())
                .customerPhone(employeeCustomer.getCustomer().getPhoneNumber())
                .kindOfInsurance(employeeCustomer.getCustomer().getKindOfInsurance())
                .address(employeeCustomer.getCustomer().getAddress())
                .kindOfJob(employeeCustomer.getCustomer().getKindOfJob())
                .build());
    }


    private Header<List<ContractCustomerResponse>> getContractCustomerResponse(List<Contract> allByContractStatus) {
        return Header.OK(allByContractStatus.stream().map(
                        cs -> ContractCustomerResponse.builder()
                                .insuranceName(cs.getInsurance().getInsuranceName())
                                .insuranceStatus(cs.getInsurance().getInsuranceStatus())
                                .customerName(cs.getCustomer().getName())
                                .address(cs.getCustomer().getAddress())
                                .fee(cs.getInsurance().getFee())
                                .healthInformation(cs.getCustomer().getHealthInformation())
                                .kindOfJob(cs.getCustomer().getKindOfJob())
                                .phoneNum(cs.getCustomer().getPhoneNumber())
                                .build())
                .collect(Collectors.toList()));
    }

    @Transactional
    @Override
    public Header<InsuranceResponse> create(InsuranceSaveRequest insuranceSaveRequest, HttpServletRequest request){
        Employee employee = employeeRepository.findById(getEmployeeId(request)).orElseThrow(EmployeeNotFoundException::new);
        validateRole(employee.getDepartment(),"상품개발팀");
        InsuranceCondition insuranceCondition = new InsuranceCondition(
                insuranceSaveRequest.getMaxAge(),
                insuranceSaveRequest.getMinAge(),
                Grade.getGrade(insuranceSaveRequest.getSmoke()),
                Grade.getGrade(insuranceSaveRequest.getAlcohol()),
                Grade.getGrade(insuranceSaveRequest.getCancer()));
        Insurance insurance = insuranceRepository.save(insuranceSaveRequest.toEntityWith(insuranceCondition));
        return Header.CREATED(InsuranceResponse.toDto(insurance));
    }

    public Long getEmployeeId(HttpServletRequest request) {
        return Long.parseLong(request.getHeader("userid"));
    }

    public void validateRole(Department department, String departmentName){
        if (!department.getDepartmentName().equals(departmentName)) throw new EmployeeAuthFailException();
    }
}
