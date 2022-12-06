package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.logic;

import com.example.insuranceSystem.domain.common.entity.EmployeeCustomer;
import com.example.insuranceSystem.domain.common.repository.EmployeeCustomerRepository;
import com.example.insuranceSystem.domain.contract.repository.ContractRepository;
import com.example.insuranceSystem.domain.contract.repository.entity.Contract;
import com.example.insuranceSystem.domain.customerService.repository.CustomerRepository;
import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import com.example.insuranceSystem.domain.insurance.exception.execute.InsuranceNotFoundException;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.InsuranceResponse;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.exception.ConsultNotFoundException;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.exception.CustomerNotFoundException;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.exception.NoConsultException;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.exception.NothingJoinedInsuranceException;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.request.EvaluateSatisfactionRequest;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.request.JoinInsuranceRequest;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response.ConsultInfoResponse;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response.JoinInsuranceResponse;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response.PaymentResponse;
import com.example.insuranceSystem.domain.insurance.repository.InsuranceRepository;
import com.example.insuranceSystem.domain.insurance.repository.entity.Insurance;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.KindOfInsurance;
import com.example.insuranceSystem.global.web.response.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class InsuranceCustomerService {

    private final EmployeeCustomerRepository employeeCustomerRepository;
    private final CustomerRepository customerRepository;
    private final InsuranceRepository insuranceRepository;
    private final ContractRepository contractRepository;

    @Transactional
    public Header<Void> lineUpCustomerConsult(HttpServletRequest request) {
        Customer customer = customerRepository
                .findById(getUserId(request)).orElseThrow(() -> new CustomerNotFoundException(getUserId(request)));
        employeeCustomerRepository.save(new EmployeeCustomer(customer));
        return Header.OK();
    }

    public Header<List<InsuranceResponse>> getInsuranceListOf(String kindOfInsurance) {
        List<Insurance> insuranceList = insuranceRepository
                .findAllByKindOfInsurance(KindOfInsurance.getKindOfInsuranceByName(kindOfInsurance));
        List<InsuranceResponse> insuranceResponseList = new ArrayList<>();
        insuranceList.forEach((i) -> insuranceResponseList.add(InsuranceResponse.toDto(i)));
        return Header.OK(insuranceResponseList);
    }

    @Transactional
    public Header<JoinInsuranceResponse> requestJoiningInsurance(JoinInsuranceRequest joinInsuranceRequest, HttpServletRequest request){
        Customer customer = customerRepository
                .findById(getUserId(request)).orElseThrow(() -> new CustomerNotFoundException(getUserId(request)));
        Insurance insurance = insuranceRepository
                .findById(joinInsuranceRequest.getInsuranceId()).orElseThrow(InsuranceNotFoundException::new);
        Contract contract = new Contract(customer, insurance);
        contractRepository.save(contract);
        return Header.OK(
                JoinInsuranceResponse.toDto(
                    insurance.getInsuranceName(),
                    insurance.getKindOfInsurance().name(),
                    contract.getContractStatus().toString())
        );
    }

    public Header<List<InsuranceResponse>> getJoinedInsurances(HttpServletRequest request) {
        Customer customer = customerRepository
                .findById(getUserId(request)).orElseThrow(() -> new CustomerNotFoundException(getUserId(request)));
        List<Contract> contracts = contractRepository.findAllByCustomer(customer).orElseThrow(NothingJoinedInsuranceException::new);
        List<Insurance> joinedInsurances = insuranceRepository.findAllByContractsIn(contracts);
        List<InsuranceResponse> insuranceResponseList = new ArrayList<>();
        joinedInsurances.forEach((i) -> insuranceResponseList.add(InsuranceResponse.toDto(i)));
        return Header.OK(insuranceResponseList);
    }

    public Header<List<ConsultInfoResponse>> getEndOfConsultList(HttpServletRequest request){
        Customer customer = customerRepository
                .findById(getUserId(request)).orElseThrow(() -> new CustomerNotFoundException(getUserId(request)));
        List<EmployeeCustomer> employeeCustomers =
                employeeCustomerRepository.findAllByCustomer(customer).orElseThrow(NoConsultException::new);
        List<ConsultInfoResponse> consultInfoResponseList = new ArrayList<>();
        employeeCustomers.forEach((ec) -> consultInfoResponseList.add(ConsultInfoResponse.toDto(ec)));
        return Header.OK(consultInfoResponseList);
    }

    @Transactional
    public Header<Void> evaluateSatisfaction(EvaluateSatisfactionRequest evaluateSatisfactionRequest,
                                                                  HttpServletRequest request){
        EmployeeCustomer employeeCustomer = employeeCustomerRepository
                .findById(evaluateSatisfactionRequest.getConsultId()).orElseThrow(ConsultNotFoundException::new);
        employeeCustomer.evaluateSatisfaction(evaluateSatisfactionRequest.getSatisfaction());
        return Header.OK();
    }


    public Long getUserId(HttpServletRequest request) {
        return Long.parseLong(request.getHeader("userId"));
    }

    public Header<List<PaymentResponse>> getPaymentHistory(HttpServletRequest request) {
        return null;
    }


}
