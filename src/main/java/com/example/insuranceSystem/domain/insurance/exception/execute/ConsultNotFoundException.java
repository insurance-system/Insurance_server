package com.example.insuranceSystem.domain.insurance.exception.execute;

import com.example.insuranceSystem.domain.insurance.exception.InsuranceException;
import org.springframework.http.HttpStatus;

import static com.example.insuranceSystem.domain.insurance.exception.InsuranceExceptionEnum.NOT_FOUND;
import static com.example.insuranceSystem.domain.insurance.exception.InsuranceExceptionEnum.NOT_FOUND_CONSULT;

public class ConsultNotFoundException extends InsuranceException {
    public ConsultNotFoundException() {
        super(NOT_FOUND_CONSULT.getCODE(), NOT_FOUND.getHttpStatus(), NOT_FOUND_CONSULT.getMESSAGE());
    }
}
