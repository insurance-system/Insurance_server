package com.example.insuranceSystem.domain.insurance.exception.execute;

import com.example.insuranceSystem.domain.insurance.exception.InsuranceException;

import static com.example.insuranceSystem.domain.insurance.exception.InsuranceExceptionEnum.NOT_FOUND_CONTRACT;

public class NotFoundContractException extends InsuranceException {
    public NotFoundContractException() {
        super(NOT_FOUND_CONTRACT.getCODE(), NOT_FOUND_CONTRACT.getHttpStatus(), NOT_FOUND_CONTRACT.getMESSAGE());
    }
}
