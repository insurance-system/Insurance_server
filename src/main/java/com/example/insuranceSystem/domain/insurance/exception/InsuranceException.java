package com.example.insuranceSystem.domain.insurance.exception;

import com.example.insuranceSystem.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class InsuranceException extends ApplicationException {
    protected InsuranceException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
