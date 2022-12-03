package com.example.insuranceSystem.domain.insurance.exception.execute;

import com.example.insuranceSystem.domain.insurance.exception.InsuranceException;
import org.springframework.http.HttpStatus;

import static com.example.insuranceSystem.domain.insurance.exception.InsuranceExceptionEnum.NOT_FOUND;

public class InsuranceNotFoundException extends InsuranceException {
    public InsuranceNotFoundException() {
        super(NOT_FOUND.getCODE(), NOT_FOUND.getHttpStatus(), NOT_FOUND.getMESSAGE());
    }
}
