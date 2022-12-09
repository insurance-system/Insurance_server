package com.example.insuranceSystem.domain.contract.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ContractExceptionEnum {
    NOT_FOUND("CT0001", HttpStatus.NO_CONTENT,"해당 계약 정보를 찾을 수 없습니다."),
    NOT_FOUND_FOR_UW("CT0002", HttpStatus.NO_CONTENT,"인수심사할 계약 정보가 없습니다."),;

    public final String CODE;
    public final HttpStatus httpStatus;
    public final String MESSAGE;
}
