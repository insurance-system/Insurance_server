package com.example.insuranceSystem.domain.insurance.exception.execute;

import com.example.insuranceSystem.domain.insurance.exception.InsuranceException;

import static com.example.insuranceSystem.domain.insurance.exception.InsuranceExceptionEnum.NO_CONTRACT_TO_UW;

public class NoContractToUwException extends InsuranceException {
    public NoContractToUwException() {
        super(NO_CONTRACT_TO_UW.getCODE(), NO_CONTRACT_TO_UW.getHttpStatus(), NO_CONTRACT_TO_UW.getMESSAGE());
    }
}
