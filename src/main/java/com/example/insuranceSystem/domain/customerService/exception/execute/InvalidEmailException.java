package com.example.insuranceSystem.domain.customerService.exception.execute;

import com.example.insuranceSystem.domain.customerService.exception.CustomerException;

import static com.example.insuranceSystem.domain.customerService.exception.CustomerExceptionEnum.INVALID_EMAIL;

public class InvalidEmailException extends CustomerException {
    public InvalidEmailException() {
        super(INVALID_EMAIL.getCODE(), INVALID_EMAIL.getHttpStatus(), INVALID_EMAIL.getMESSAGE());
    }
}