package com.example.insuranceSystem.domain.employeeService.logic;

import com.example.insuranceSystem.domain.customerService.exception.execute.WrongPasswordException;
import com.example.insuranceSystem.domain.employeeService.exception.execute.EmployeeNotFoundException;
import com.example.insuranceSystem.domain.employeeService.repository.EmployeeRepository;
import com.example.insuranceSystem.domain.employeeService.repository.entity.Employee;
import com.example.insuranceSystem.domain.employeeService.web.dto.request.LoginEmployeeRequest;
import com.example.insuranceSystem.domain.employeeService.web.dto.response.EmployeeLoginResponse;
import com.example.insuranceSystem.global.web.response.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // 사원 로그인
    public Header<?> login(LoginEmployeeRequest loginEmployeeRequest) {
        Employee employee = employeeRepository.findByEmail(loginEmployeeRequest.getEmail()).orElseThrow(EmployeeNotFoundException::new);
        if(!loginEmployeeRequest.getPassword().equals(employee.getPassword())) throw new WrongPasswordException();
        return Header.OK(new EmployeeLoginResponse(employee.getEmployeeId(), employee.getName(), employee.getKindOfRole().getName()));
    }
}