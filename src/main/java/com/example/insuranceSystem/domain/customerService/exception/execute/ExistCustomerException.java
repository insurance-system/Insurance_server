package com.example.insuranceSystem.domain.customerService.exception.execute;

import com.example.insuranceSystem.domain.customerService.exception.CustomerException;

import static com.example.insuranceSystem.domain.customerService.exception.CustomerExceptionEnum.EXIST;

public class ExistCustomerException extends CustomerException {
    public ExistCustomerException() {
        super(EXIST.getCODE(), EXIST.getHttpStatus(), EXIST.getMESSAGE());
    }
}