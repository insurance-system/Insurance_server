package com.example.insuranceSystem.domain.contract.exception.execute;

import com.example.insuranceSystem.domain.contract.exception.ContractException;

import static com.example.insuranceSystem.domain.contract.exception.ContractExceptionEnum.NOT_FOUND_FOR_UW;

public class NoContractToUwException extends ContractException {
    public NoContractToUwException() {
        super(NOT_FOUND_FOR_UW.getCODE(), NOT_FOUND_FOR_UW.getHttpStatus(), NOT_FOUND_FOR_UW.getMESSAGE());
    }
}
