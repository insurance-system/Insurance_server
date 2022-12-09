package com.example.insuranceSystem.domain.insurance.exception.execute;

import com.example.insuranceSystem.domain.insurance.exception.InsuranceException;

import static com.example.insuranceSystem.domain.insurance.exception.InsuranceExceptionEnum.NO_INCIDENT_LOG;

public class NoIncidentLogListException extends InsuranceException {
    public NoIncidentLogListException() {
        super(NO_INCIDENT_LOG.getCODE(), NO_INCIDENT_LOG.getHttpStatus(), NO_INCIDENT_LOG.getMESSAGE());
    }
}
