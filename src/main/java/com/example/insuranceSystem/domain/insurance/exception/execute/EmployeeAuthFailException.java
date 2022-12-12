package com.example.insuranceSystem.domain.insurance.exception.execute;

import com.example.insuranceSystem.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

import static com.example.insuranceSystem.domain.insurance.exception.InsuranceExceptionEnum.AUTH_FAIL;

public class EmployeeAuthFailException extends ApplicationException {
    public EmployeeAuthFailException() {super(AUTH_FAIL.getCODE(), AUTH_FAIL.getHttpStatus(), AUTH_FAIL.getMESSAGE());}
}
