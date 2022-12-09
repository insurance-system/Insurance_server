package com.example.insuranceSystem.domain.insurance.exception.execute;

import com.example.insuranceSystem.domain.insurance.exception.InsuranceException;

import static com.example.insuranceSystem.domain.insurance.exception.InsuranceExceptionEnum.NO_LECTURE_LIST;

public class NoLectureListException extends InsuranceException {
    public NoLectureListException() {
        super(NO_LECTURE_LIST.getCODE(), NO_LECTURE_LIST.getHttpStatus(), NO_LECTURE_LIST.getMESSAGE());
    }
}
