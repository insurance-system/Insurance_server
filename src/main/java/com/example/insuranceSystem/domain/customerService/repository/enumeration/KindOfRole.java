package com.example.insuranceSystem.domain.customerService.repository.enumeration;

import lombok.Getter;

@Getter
public enum KindOfRole {
    CUSTOMER(1, "customer"),
    EMPLOYEE(2, "employee");

    private int number;
    private String name;

    KindOfRole(int number, String name) {
        this.number = number;
        this.name = name;
    }
}
