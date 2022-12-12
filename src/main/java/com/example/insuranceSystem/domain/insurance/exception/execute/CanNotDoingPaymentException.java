package com.example.insuranceSystem.domain.insurance.exception.execute;

import com.example.insuranceSystem.domain.insurance.exception.InsuranceException;

import static com.example.insuranceSystem.domain.insurance.exception.InsuranceExceptionEnum.*;

public class CanNotDoingPaymentException extends InsuranceException {
    public CanNotDoingPaymentException() {
        super(CANNOT_DOING_PAYMENT.getCODE(), CANNOT_DOING_PAYMENT.getHttpStatus(), CANNOT_DOING_PAYMENT.getMESSAGE());
    }
}
