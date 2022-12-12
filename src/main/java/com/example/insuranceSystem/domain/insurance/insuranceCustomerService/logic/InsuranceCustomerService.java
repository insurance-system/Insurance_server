package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.logic;

import com.example.insuranceSystem.domain.common.entity.EmployeeCustomer;
import com.example.insuranceSystem.domain.common.entity.IncidentLog;
import com.example.insuranceSystem.domain.common.entity.InsuranceClaim;
import com.example.insuranceSystem.domain.common.entity.Payment;
import com.example.insuranceSystem.domain.common.repository.EmployeeCustomerRepository;
import com.example.insuranceSystem.domain.common.repository.InsuranceClaimRepository;
import com.example.insuranceSystem.domain.common.repository.IncidentLogRepository;
import com.example.insuranceSystem.domain.common.repository.PaymentRepository;
import com.example.insuranceSystem.domain.contract.repository.ContractRepository;
import com.example.insuranceSystem.domain.contract.repository.entity.Contract;
import com.example.insuranceSystem.domain.customerService.exception.execute.CustomerNotFoundException;
import com.example.insuranceSystem.domain.customerService.repository.CustomerRepository;
import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import com.example.insuranceSystem.domain.insurance.exception.execute.*;
import com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.response.InsuranceResponse;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.request.*;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response.ConsultInfoResponse;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response.JoinInsuranceResponse;
import com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response.PaymentResponse;
import com.example.insuranceSystem.domain.insurance.repository.InsuranceRepository;
import com.example.insuranceSystem.domain.insurance.repository.entity.Insurance;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.KindOfInsurance;
import com.example.insuranceSystem.global.enumerations.ContractStatus;
import com.example.insuranceSystem.global.web.response.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class InsuranceCustomerService{

    private final EmployeeCustomerRepository employeeCustomerRepository;
    private final CustomerRepository customerRepository;
    private final InsuranceRepository insuranceRepository;
    private final IncidentLogRepository insuranceLogRepository;
    private final InsuranceClaimRepository insuranceClaimRepository;
    private final ContractRepository contractRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public Header<Void> lineUpCustomerConsult(HttpServletRequest request){
        Customer customer = customerRepository
                .findById(getUserId(request)).orElseThrow(CustomerNotFoundException::new);
        employeeCustomerRepository.save(new EmployeeCustomer(customer));
        return Header.OK();
    }

    public Header<List<InsuranceResponse>> getInsuranceListOf(String kindOfInsurance){
        List<Insurance> insuranceList = insuranceRepository
                .findAllByKindOfInsurance(KindOfInsurance.getKindOfInsuranceByName(kindOfInsurance));
        List<InsuranceResponse> insuranceResponseList = new ArrayList<>();
        insuranceList.forEach((i) -> insuranceResponseList.add(InsuranceResponse.toDto(i)));
        return Header.OK(insuranceResponseList);
    }

    @Transactional
    public Header<JoinInsuranceResponse> requestJoiningInsurance(JoinInsuranceRequest joinInsuranceRequest,
                                                                 HttpServletRequest request){
        Customer customer = customerRepository
                .findById(getUserId(request)).orElseThrow(CustomerNotFoundException::new);
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

    public Header<List<InsuranceResponse>> getJoinedInsurances(HttpServletRequest request){
        Customer customer = customerRepository
                .findById(getUserId(request)).orElseThrow(CustomerNotFoundException::new);
        List<Contract> contracts = contractRepository
                .findAllByCustomer(customer).orElseThrow(NothingJoinedInsuranceException::new);
        List<Insurance> joinedInsurances = insuranceRepository.findAllByContractsIn(contracts);
        List<InsuranceResponse> insuranceResponseList = new ArrayList<>();
        joinedInsurances.forEach((i) -> insuranceResponseList.add(InsuranceResponse.toDto(i)));
        return Header.OK(insuranceResponseList);
    }

    public Header<List<ConsultInfoResponse>> getEndOfConsultList(HttpServletRequest request){
        Customer customer = customerRepository
                .findById(getUserId(request)).orElseThrow(CustomerNotFoundException::new);
        List<EmployeeCustomer> employeeCustomers =
                employeeCustomerRepository.findAllByCustomer(customer).orElseThrow(ConsultNotFoundException::new);
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

    @Transactional
    public Header<Void> doPayment(PaymentRequest paymentRequest, HttpServletRequest request){
        Customer customer = customerRepository
                .findById(getUserId(request)).orElseThrow(CustomerNotFoundException::new);

        Contract contract = contractRepository
                .findById(paymentRequest.getContractId()).orElseThrow(NotFoundContractException::new);
        if(!contract.getContractStatus().equals(ContractStatus.CLEAR)) throw new ContractStatusIsNotClearException();

        List<Payment> paymentHistory = paymentRepository.findAllByCustomerAndContractOrderByCreatedDate(customer, contract);
        if(paymentHistory.isEmpty() || isPaymentPossible(paymentHistory.get(0))){
            Payment payment = new Payment(paymentRequest.getPayCost(), contract, customer);
            paymentRepository.save(payment);
        }
        else throw new CanNotDoingPaymentException();

        return Header.OK();
    }

    private boolean isPaymentPossible(Payment payment) {
        LocalDate createdDate = payment.getCreatedDate().toLocalDate();
        LocalDate now = LocalDateTime.now().toLocalDate();
        Period period = Period.between(createdDate, now);
        return period.getDays() >= 27;
    }

    public Header<List<PaymentResponse>> getPaymentHistory(HttpServletRequest request){
        Customer customer = customerRepository
                .findById(getUserId(request)).orElseThrow(CustomerNotFoundException::new);

        List<Payment> payments = paymentRepository
                .findAllByCustomer(customer).orElseThrow(PaymentNotFoundException::new);

        ArrayList<PaymentResponse> paymentResponses = new ArrayList<>();
        payments.forEach((p) -> paymentResponses.add(PaymentResponse.toDto(p)));
        return Header.OK(paymentResponses);
    }

    @Transactional
    public Header<Void> acceptIncidentHandling(IncidentRequest incidentRequest,
                                               HttpServletRequest request){
        Customer customer = customerRepository
                .findById(getUserId(request)).orElseThrow(CustomerNotFoundException::new);
        IncidentLog incidentLog = new IncidentLog(incidentRequest.toEntity(), customer);
        insuranceLogRepository.save(incidentLog);
        return Header.OK();
    }

    @Transactional
    public Header<Void> claimInsurance(ClaimInsuranceRequest claimInsuranceRequest, HttpServletRequest request){
        Customer customer = customerRepository
                .findById(getUserId(request)).orElseThrow(CustomerNotFoundException::new);

        Insurance insurance = insuranceRepository
                .findById(claimInsuranceRequest.getInsuranceId()).orElseThrow(InsuranceNotFoundException::new);

        InsuranceClaim insuranceClaim = new InsuranceClaim(claimInsuranceRequest.toEntity(), customer, insurance);
        insuranceClaimRepository.save(insuranceClaim);
        return Header.OK();
    }

    public Long getUserId(HttpServletRequest request) {
        return Long.parseLong(request.getHeader("userid"));
    }

}