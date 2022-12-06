package com.example.insuranceSystem.domain.customerService.exception;

import com.example.insuranceSystem.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class CustomerException extends ApplicationException {
    protected CustomerException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
