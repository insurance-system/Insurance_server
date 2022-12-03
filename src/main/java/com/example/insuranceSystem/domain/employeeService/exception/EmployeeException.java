package com.example.insuranceSystem.domain.employeeService.exception;

import com.example.insuranceSystem.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class EmployeeException extends ApplicationException {
    protected EmployeeException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
