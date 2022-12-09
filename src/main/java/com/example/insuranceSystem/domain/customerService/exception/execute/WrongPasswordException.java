package com.example.insuranceSystem.domain.customerService.exception.execute;

import com.example.insuranceSystem.domain.customerService.exception.CustomerException;

import static com.example.insuranceSystem.domain.customerService.exception.CustomerExceptionEnum.WRONG_PASSWORD;

public class WrongPasswordException extends CustomerException {
    public WrongPasswordException() {
        super(WRONG_PASSWORD.getCODE(), WRONG_PASSWORD.getHttpStatus(), WRONG_PASSWORD.getMESSAGE());
    }
}