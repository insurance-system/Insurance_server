package com.example.insuranceSystem.global.enumerations;


import lombok.Getter;

import java.util.Objects;

@Getter
public enum KindOfJob {

    OFFICE_WORKER(1, "office_worker"),
    STUDENT(2, "student"),
    HOUSE_MAKER(3, "house_maker"),
    NOT_EMPLOYED(4, "not_employed");

    private int number;
    private String name;

    KindOfJob(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public static KindOfJob getKindOfJobBy(int number) {
        if(number == 1) return KindOfJob.OFFICE_WORKER;
        else if(number == 2) return KindOfJob.STUDENT;
        else if(number == 3) return KindOfJob.HOUSE_MAKER;
        else return KindOfJob.NOT_EMPLOYED;
    }

    public static KindOfJob getKindOfJobBy(String name) {
        if(Objects.equals(name, "office_worker")) return KindOfJob.OFFICE_WORKER;
        else if(Objects.equals(name, "student")) return KindOfJob.STUDENT;
        else if(Objects.equals(name, "house_maker")) return KindOfJob.HOUSE_MAKER;
        else return KindOfJob.NOT_EMPLOYED;
    }
}