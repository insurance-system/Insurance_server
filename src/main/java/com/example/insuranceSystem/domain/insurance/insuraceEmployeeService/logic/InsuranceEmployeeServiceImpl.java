package com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.logic;

import com.example.insuranceSystem.domain.contract.repository.ContractRepository;
import com.example.insuranceSystem.domain.contract.repository.entity.Contract;
import com.example.insuranceSystem.domain.customerService.exception.execute.CustomerNotFoundException;
import com.example.insuranceSystem.domain.customerService.repository.CustomerRepository;
import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import com.example.insuranceSystem.domain.insurance.exception.execute.InsuranceNotFoundException;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.request.InsuranceSaveRequest;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.CustomerInfoResponse;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.InsuranceResponse;
import com.example.insuranceSystem.domain.insurance.repository.InsuranceConditionRepository;
import com.example.insuranceSystem.domain.insurance.repository.InsuranceRepository;
import com.example.insuranceSystem.domain.insurance.repository.entity.Insurance;
import com.example.insuranceSystem.domain.insurance.repository.entity.InsuranceCondition;
import com.example.insuranceSystem.global.web.response.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly=true)
@RequiredArgsConstructor
@Service
public class InsuranceEmployeeServiceImpl implements InsuranceEmployeeService {

    private final InsuranceRepository insuranceRepository;
    private final InsuranceConditionRepository insuranceConditionRepository;
    private final ContractRepository contractRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Header<InsuranceResponse> getInsurance(Long id, HttpServletRequest request) {
        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(InsuranceNotFoundException::new);
        return Header.OK(InsuranceResponse.create(insurance));
    }

    @Override
    public Header<CustomerInfoResponse> getCustomerandJoinedInsurance(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        List<Contract> contractList = contractRepository.findByCustomer_id(id);
        List<InsuranceResponse> insuranceList = new ArrayList<>();
        for (Contract contract : contractList) {
            Insurance insurance = insuranceRepository.findById(contract.getInsurance().getInsuranceId()).orElseThrow(null);
            insuranceList.add(InsuranceResponse.create(insurance));
        }
        return Header.OK(CustomerInfoResponse.create(customer, insuranceList));
    }

    @Transactional
    @Override
    public Header<InsuranceResponse> create(InsuranceSaveRequest insuranceSaveRequest, HttpServletRequest request){
        InsuranceCondition insuranceCondition = insuranceConditionRepository.save(insuranceSaveRequest.toInsuranceConditionEntity());
        Insurance insurance = insuranceRepository.save(insuranceSaveRequest.toEntityWith(insuranceCondition));
        return Header.CREATED(InsuranceResponse.create(insurance));
    }
}
