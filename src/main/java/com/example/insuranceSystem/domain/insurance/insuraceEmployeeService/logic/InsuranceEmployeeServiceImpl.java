package com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.logic;

import com.example.insuranceSystem.domain.contract.exception.execute.NoContractException;
import com.example.insuranceSystem.domain.contract.exception.execute.NoContractToUwException;
import com.example.insuranceSystem.domain.contract.repository.ContractRepository;
import com.example.insuranceSystem.domain.contract.repository.entity.Contract;
import com.example.insuranceSystem.domain.customerService.exception.execute.CustomerNotFoundException;
import com.example.insuranceSystem.domain.customerService.repository.CustomerRepository;
import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import com.example.insuranceSystem.domain.employeeService.exception.execute.EmployeeNotFoundException;
import com.example.insuranceSystem.domain.employeeService.repository.EmployeeRepository;
import com.example.insuranceSystem.domain.employeeService.repository.LectureRepository;
import com.example.insuranceSystem.domain.employeeService.repository.entity.Employee;
import com.example.insuranceSystem.domain.employeeService.repository.entity.Lecture;
import com.example.insuranceSystem.domain.insurance.exception.execute.InsuranceNotFoundException;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.request.InsuranceSaveRequest;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.request.LectureRequest;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.request.StartUwRequest;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.CustomerInfoResponse;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.InsuranceResponse;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.LectureResponse;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.UwListResponse;
import com.example.insuranceSystem.domain.insurance.repository.InsuranceConditionRepository;
import com.example.insuranceSystem.domain.insurance.repository.InsuranceRepository;
import com.example.insuranceSystem.domain.insurance.repository.entity.Insurance;
import com.example.insuranceSystem.domain.insurance.repository.entity.InsuranceCondition;
import com.example.insuranceSystem.global.enumerations.ContractStatus;
import com.example.insuranceSystem.global.web.response.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly=true)
@RequiredArgsConstructor
@Service
public class InsuranceEmployeeServiceImpl implements InsuranceEmployeeService {

    private final InsuranceRepository insuranceRepository;
    private final InsuranceConditionRepository insuranceConditionRepository;
    private final ContractRepository contractRepository;
    private final CustomerRepository customerRepository;
    private final LectureRepository lectureRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Header<InsuranceResponse> getInsurance(Long id) {
        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(InsuranceNotFoundException::new);
        return Header.OK(InsuranceResponse.toDto(insurance));
    }

    // 특정 id의 고객 및 가입된 보험 정보 출력
    @Override
    public Header<CustomerInfoResponse> getCustomerandJoinedInsurance(Long id) {
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
    public Header<List<LectureResponse>> getLectureList() {
        List<Lecture> lectureList = lectureRepository.findAll();
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
        lectureRepository.save(new Lecture(
                lectureRequest.getLectureName(),
                lectureRequest.getLectureUrl(),
                employee));
        return Header.OK();
    }

    // 인수심사 리스트 출력
    @Override
    public Header<List<UwListResponse>> getUwList() {
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
    public Header<Void> startUw(StartUwRequest startUwRequest) {
        Contract contract = contractRepository.findById(startUwRequest.getContractId()).orElseThrow(NoContractException::new);
        contract.setContractStatus(ContractStatus.getContractStatusByName(startUwRequest.getContractStatus()));
        contractRepository.save(contract);
        return Header.OK();
    }

    @Transactional
    @Override
    public Header<InsuranceResponse> create(InsuranceSaveRequest insuranceSaveRequest){
        InsuranceCondition insuranceCondition = insuranceConditionRepository.save(insuranceSaveRequest.toInsuranceConditionEntity());
        Insurance insurance = insuranceRepository.save(insuranceSaveRequest.toEntityWith(insuranceCondition));
        return Header.CREATED(InsuranceResponse.toDto(insurance));
    }

    public Long getEmployeeId(HttpServletRequest request) {
        return Long.parseLong(request.getHeader("userid"));
    }
}
