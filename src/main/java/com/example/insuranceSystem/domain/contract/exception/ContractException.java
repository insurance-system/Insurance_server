package com.example.insuranceSystem.domain.contract.exception;

import com.example.insuranceSystem.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class ContractException extends ApplicationException {
    protected ContractException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
