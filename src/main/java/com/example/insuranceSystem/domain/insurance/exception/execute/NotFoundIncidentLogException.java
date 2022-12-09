package com.example.insuranceSystem.domain.insurance.exception.execute;

import com.example.insuranceSystem.domain.insurance.exception.InsuranceException;

import static com.example.insuranceSystem.domain.insurance.exception.InsuranceExceptionEnum.NOT_FOUND_INCIDENT_LOG;

public class NotFoundIncidentLogException extends InsuranceException {
    public NotFoundIncidentLogException() {
        super(NOT_FOUND_INCIDENT_LOG.getCODE(), NOT_FOUND_INCIDENT_LOG.getHttpStatus(), NOT_FOUND_INCIDENT_LOG.getMESSAGE());
    }
}
