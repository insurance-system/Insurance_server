package com.example.insuranceSystem.domain.insurance.exception.execute;

import com.example.insuranceSystem.domain.insurance.exception.InsuranceException;

import static com.example.insuranceSystem.domain.insurance.exception.InsuranceExceptionEnum.*;

public class PaymentNotFoundException extends InsuranceException {
    public PaymentNotFoundException() {
        super(NOT_FOUND_CONTRACT.getCODE(), NOT_FOUND.getHttpStatus(), NOT_FOUND_CONTRACT.getMESSAGE());
    }
}
