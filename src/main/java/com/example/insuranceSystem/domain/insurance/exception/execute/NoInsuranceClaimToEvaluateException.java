package com.example.insuranceSystem.domain.insurance.exception.execute;

import com.example.insuranceSystem.domain.insurance.exception.InsuranceException;

import static com.example.insuranceSystem.domain.insurance.exception.InsuranceExceptionEnum.NO_INSURANCE_CLAIM_TO_EVALUATE;

public class NoInsuranceClaimToEvaluateException extends InsuranceException {
    public NoInsuranceClaimToEvaluateException() {
        super(NO_INSURANCE_CLAIM_TO_EVALUATE.getCODE(), NO_INSURANCE_CLAIM_TO_EVALUATE.getHttpStatus(), NO_INSURANCE_CLAIM_TO_EVALUATE.getMESSAGE());
    }
}
