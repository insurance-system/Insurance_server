package com.example.insuranceSystem.domain.contract.exception.execute;

import com.example.insuranceSystem.domain.contract.exception.ContractException;

import static com.example.insuranceSystem.domain.contract.exception.ContractExceptionEnum.NOT_FOUND;

public class NoContractException extends ContractException {
    public NoContractException() {
        super(NOT_FOUND.getCODE(), NOT_FOUND.getHttpStatus(), NOT_FOUND.getMESSAGE());
    }
}
