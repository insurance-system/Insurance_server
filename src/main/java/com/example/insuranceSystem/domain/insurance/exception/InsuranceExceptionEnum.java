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

    NOT_FOUND_INSURANCE_CLAIM("IC001", HttpStatus.NOT_FOUND, "보험금 청구 내역을 찾을 수 없습니다."),
    NO_INSURANCE_CLAIM_TO_EVALUATE("IC002", HttpStatus.NO_CONTENT, "심사할 보험금 청구 내역이 없습니다."),

    NOT_FOUND_INCIDENT_LOG("IL001", HttpStatus.NOT_FOUND, "사고 접수 내역을 찾을 수 없습니다."),
    NO_INCIDENT_LOG("IL002", HttpStatus.NO_CONTENT, "사고 접수 내역이 없습니다."),

    NO_LECTURE_LIST("L001", HttpStatus.NO_CONTENT, "등록된 영업 교육 강의 리스트가 없습니다.");

    public final String CODE;
    public final HttpStatus httpStatus;
    public final String MESSAGE;
}
