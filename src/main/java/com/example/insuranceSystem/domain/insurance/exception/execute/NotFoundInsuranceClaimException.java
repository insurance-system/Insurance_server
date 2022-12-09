package com.example.insuranceSystem.domain.insurance.exception.execute;

import com.example.insuranceSystem.domain.insurance.exception.InsuranceException;

import static com.example.insuranceSystem.domain.insurance.exception.InsuranceExceptionEnum.NOT_FOUND_INSURANCE_CLAIM;

public class NotFoundInsuranceClaimException extends InsuranceException {
    public NotFoundInsuranceClaimException() {
        super(NOT_FOUND_INSURANCE_CLAIM.getCODE(), NOT_FOUND_INSURANCE_CLAIM.getHttpStatus(), NOT_FOUND_INSURANCE_CLAIM.getMESSAGE());
    }
}
