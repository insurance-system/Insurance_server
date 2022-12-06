package com.example.insuranceSystem.domain.customerService.exception.execute;

import com.example.insuranceSystem.domain.customerService.exception.CustomerException;

import static com.example.insuranceSystem.domain.customerService.exception.CustomerExceptionEnum.NOT_FOUND;

public class CustomerNotFoundException extends CustomerException {
    public CustomerNotFoundException() {
        super(NOT_FOUND.getCODE(), NOT_FOUND.getHttpStatus(), NOT_FOUND.getMESSAGE());
    }
}