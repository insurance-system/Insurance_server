package com.example.insuranceSystem.domain.insurance.exception.execute;

import com.example.insuranceSystem.domain.insurance.exception.InsuranceException;
import org.springframework.http.HttpStatus;

import static com.example.insuranceSystem.domain.insurance.exception.InsuranceExceptionEnum.NO_JOINED_INSURANCE;

public class NothingJoinedInsuranceException extends InsuranceException {

    public NothingJoinedInsuranceException() {
        super(NO_JOINED_INSURANCE.getCODE(), NO_JOINED_INSURANCE.getHttpStatus(), NO_JOINED_INSURANCE.getMESSAGE());
    }
}
