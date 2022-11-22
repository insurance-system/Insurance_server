package com.example.insuranceSystem.domain.insurance.repository.entity.enumeration;

public enum InsuranceStatus {
    UNDER_EXAMINATION(1, "UNDER_EXAMINATION"),
    PERMISSION(2,"PERMISSION"),
    NOT_ALLOWED(3, "NOT_ALLOWED");

    private int number;
    private String name;

    InsuranceStatus(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public static InsuranceStatus getInsuranceStatus(int number){
        if(number == 1) return InsuranceStatus.UNDER_EXAMINATION;
        else if(number == 2)return InsuranceStatus.PERMISSION;
        else return InsuranceStatus.NOT_ALLOWED;
    }

    public static InsuranceStatus getInsuranceStatus(String name){
        if(name.equals("UNDER_EXAMINATION")) return InsuranceStatus.UNDER_EXAMINATION;
        else if(name.equals("PERMISSION"))return InsuranceStatus.PERMISSION;
        else return InsuranceStatus.NOT_ALLOWED;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
