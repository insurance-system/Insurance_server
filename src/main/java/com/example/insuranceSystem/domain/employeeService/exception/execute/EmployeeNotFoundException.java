package com.example.insuranceSystem.domain.employeeService.exception.execute;

import com.example.insuranceSystem.domain.employeeService.exception.EmployeeException;

import static com.example.insuranceSystem.domain.employeeService.exception.EmployeeExceptionEnum.NOT_FOUND;


public class EmployeeNotFoundException extends EmployeeException {
    public EmployeeNotFoundException() {
        super(NOT_FOUND.getCODE(), NOT_FOUND.getHttpStatus(), NOT_FOUND.getMESSAGE());
    }
}
