package com.example.insuranceSystem.domain.customerService.exception.execute;

import com.example.insuranceSystem.domain.customerService.exception.CustomerException;

import static com.example.insuranceSystem.domain.customerService.exception.CustomerExceptionEnum.INVALID_PHONE;

public class InvalidPhoneException extends CustomerException {
    public InvalidPhoneException() {
        super(INVALID_PHONE.getCODE(), INVALID_PHONE.getHttpStatus(), INVALID_PHONE.getMESSAGE());
    }
}