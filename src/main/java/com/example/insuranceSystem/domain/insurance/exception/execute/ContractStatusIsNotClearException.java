package com.example.insuranceSystem.domain.insurance.exception.execute;

import com.example.insuranceSystem.domain.insurance.exception.InsuranceException;

import static com.example.insuranceSystem.domain.insurance.exception.InsuranceExceptionEnum.*;

public class ContractStatusIsNotClearException extends InsuranceException {
    public ContractStatusIsNotClearException() {
        super(NOT_CLEAR_CONTRACT.getCODE(), NOT_CLEAR_CONTRACT.getHttpStatus(), NOT_CLEAR_CONTRACT.getMESSAGE());
    }
}
