package com.example.insuranceSystem.domain.customerService.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomerExceptionEnum {
    NOT_FOUND("C0001", HttpStatus.NOT_FOUND, "해당 고객을 찾을 수 없습니다."),
    EXIST("C0002", HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    WRONG_PASSWORD("C003", HttpStatus.BAD_REQUEST, "잘못된 비밀번호입니다."),
    
    INVALID_EMAIL("C1000", HttpStatus.BAD_REQUEST, "잘못된 이메일 형식입니다. 형식(mju@naver.com)"),
    INVALID_PHONE("C1001", HttpStatus.BAD_REQUEST, "잘못된 전화번호 형식입니다. 형식(010-0000-0000)"),
    INVALID_SSN("C1002", HttpStatus.BAD_REQUEST, "잘못된 주민등록번호 형식입니다. 형식(000000-000000)"),
    INVALID_ZIPCODE("C1003", HttpStatus.BAD_REQUEST, "잘못된 우편번호 형식입니다. 형식(숫자5자리)"),
    INVALID_PASSWORD("C1004", HttpStatus.BAD_REQUEST, "잘못된 비밀번호 형식입니다. 형식(숫자, 문자, 특수문자 포함 및 8-16자리)")
    ;

    public final String CODE;
    public final HttpStatus httpStatus;
    public final String MESSAGE;
}