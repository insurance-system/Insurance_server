package com.example.insuranceSystem.domain.common.repository.entity.enumerations;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum IncidentCategory {
    carToCar(1, "carToCar", "차대차"),
    carToMan(2, "carToMan", "차대인"),
    solo(3, "solo", "단독사고");

    private int number;
    private String name;
    private String korName;

    IncidentCategory(int number, String name, String korName){
        this.number = number;
        this.name = name;
        this.korName = korName;
    }

    public static IncidentCategory getIncidentCategoryByNum(int number){
        return Arrays.stream(IncidentCategory.values())
                .filter(cs -> cs.getNumber()==number)
                .findAny().orElse(null);
    }

    public static IncidentCategory getIncidentCategoryByName(String name){
        return Arrays.stream(IncidentCategory.values())
                .filter(cs -> cs.getName().equals(name))
                .findAny().orElse(null);
    }
}
