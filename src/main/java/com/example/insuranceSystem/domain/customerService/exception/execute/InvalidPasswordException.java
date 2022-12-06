package com.example.insuranceSystem.domain.customerService.exception.execute;

import com.example.insuranceSystem.domain.customerService.exception.CustomerException;

import static com.example.insuranceSystem.domain.customerService.exception.CustomerExceptionEnum.INVALID_PASSWORD;

public class InvalidPasswordException extends CustomerException {
    public InvalidPasswordException() {
        super(INVALID_PASSWORD.getCODE(), INVALID_PASSWORD.getHttpStatus(), INVALID_PASSWORD.getMESSAGE());
    }
}