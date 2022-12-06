package com.example.insuranceSystem.domain.customerService.exception.execute;

import com.example.insuranceSystem.domain.customerService.exception.CustomerException;

import static com.example.insuranceSystem.domain.customerService.exception.CustomerExceptionEnum.INVALID_ZIPCODE;

public class InvalidZipcodeException extends CustomerException {
    public InvalidZipcodeException() {
        super(INVALID_ZIPCODE.getCODE(), INVALID_ZIPCODE.getHttpStatus(), INVALID_ZIPCODE.getMESSAGE());
    }
}