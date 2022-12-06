package com.example.insuranceSystem.domain.customerService.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomerExceptionEnum {
    NOT_FOUND("C0001", HttpStatus.NOT_FOUND, "해당 고객을 찾을 수 없습니다.");

    public final String CODE;
    public final HttpStatus httpStatus;
    public final String MESSAGE;
}