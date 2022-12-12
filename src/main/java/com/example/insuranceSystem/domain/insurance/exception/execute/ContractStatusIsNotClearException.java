package com.example.insuranceSystem.domain.insurance.exception.execute;

import com.example.insuranceSystem.domain.insurance.exception.InsuranceException;

import static com.example.insuranceSystem.domain.insurance.exception.InsuranceExceptionEnum.NOT_FOUND;
import static com.example.insuranceSystem.domain.insurance.exception.InsuranceExceptionEnum.NOT_FOUND_CONSULT;

public class ContractStatusIsNotClearException extends InsuranceException {
    public ContractStatusIsNotClearException() {
        super(NOT_FOUND_CONSULT.getCODE(), NOT_FOUND.getHttpStatus(), NOT_FOUND_CONSULT.getMESSAGE());
    }
}
