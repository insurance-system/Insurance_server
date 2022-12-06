package com.example.insuranceSystem.domain.customerService.exception.execute;

import com.example.insuranceSystem.domain.customerService.exception.CustomerException;

import static com.example.insuranceSystem.domain.customerService.exception.CustomerExceptionEnum.INVALID_SSN;

public class InvalidSsnException extends CustomerException {
    public InvalidSsnException() {
        super(INVALID_SSN.getCODE(), INVALID_SSN.getHttpStatus(), INVALID_SSN.getMESSAGE());
    }
}