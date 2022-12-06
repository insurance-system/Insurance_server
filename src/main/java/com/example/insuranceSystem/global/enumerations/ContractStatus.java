package com.example.insuranceSystem.global.enumerations;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ContractStatus {

    EXPIRATION(1, "만기"),
    NON_PAYMENT(2, "미납"),
    PROGRESS_UW(3, "인수심사 진행중"),
    REFUSE_UW(4, "인수심사 거절"),
    CLEAR(5, "정상");

    private int number;
    private String name;

    ContractStatus(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public static ContractStatus getContractStatusByNum(int number) {
        return Arrays.stream(ContractStatus.values())
                .filter(cs -> cs.getNumber()==number)
                .findAny().orElse(null);
    }

    public static ContractStatus getContractStatusByName(String name) {
        return Arrays.stream(ContractStatus.values())
                .filter(cs -> cs.getName().equals(name))
                .findAny().orElse(null);
    }
}
