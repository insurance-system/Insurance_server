package com.example.insuranceSystem.domain.employeeService.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum EmployeeExceptionEnum {
    NOT_FOUND("E0001", HttpStatus.NOT_FOUND,"해당 사원 정보를 찾을 수 없습니다.");

    public final String CODE;
    public final HttpStatus httpStatus;
    public final String MESSAGE;
}
