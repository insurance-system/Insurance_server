package com.example.insuranceSystem.domain.insurance.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum InsuranceExceptionEnum {
    NOT_FOUND("I0001", HttpStatus.NOT_FOUND,"해당 보험 정보를 찾을 수 없습니다."),

    NOT_FOUND_CONTRACT("CT0001", HttpStatus.NOT_FOUND,"해당 계약 정보를 찾을 수 없습니다."),
    NO_CONTRACT_TO_UW("CT0002", HttpStatus.NO_CONTENT,"인수심사할 계약 정보가 없습니다."),
    NOT_CLEAR_CONTRACT("CT0003", HttpStatus.FORBIDDEN,"해당 계약의 상태가 명확하지 않아 보험비을 납부할 수 없습니다."),

    NOT_FOUND_INSURANCE_CLAIM("IC001", HttpStatus.NOT_FOUND, "보험금 청구 내역을 찾을 수 없습니다."),
    NO_INSURANCE_CLAIM_TO_EVALUATE("IC002", HttpStatus.NO_CONTENT, "심사할 보험금 청구 내역이 없습니다."),
    NO_JOINED_INSURANCE("I001", HttpStatus.NO_CONTENT, "해당 고객은 가입한 보험이 없습니다."),

    NOT_FOUND_INCIDENT_LOG("IL001", HttpStatus.NOT_FOUND, "사고 접수 내역을 찾을 수 없습니다."),
    NO_INCIDENT_LOG("IL002", HttpStatus.NO_CONTENT, "사고 접수 내역이 없습니다."),

    NO_LECTURE_LIST("L001", HttpStatus.NO_CONTENT, "등록된 영업 교육 강의 리스트가 없습니다."),

    NOT_FOUND_CONSULT("C001", HttpStatus.NO_CONTENT, "등록된 상담내역이 없습니다."),

    NOT_FOUND_PAYMENT("P001", HttpStatus.NO_CONTENT, "해당 납입 내역을 찾을 수 없습니다."),
    CANNOT_DOING_PAYMENT("P002", HttpStatus.FORBIDDEN, "보험비를 납부한 날로부터 얼마 지나지 않아 아직 보험비를 납부할 수 없습니다."),

    AUTH_FAIL("A0001", HttpStatus.NON_AUTHORITATIVE_INFORMATION, "접근권한이 없습니다.");


    public final String CODE;
    public final HttpStatus httpStatus;
    public final String MESSAGE;
}
