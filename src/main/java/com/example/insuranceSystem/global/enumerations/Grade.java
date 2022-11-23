package com.example.insuranceSystem.global.enumerations;


public enum Grade {
    A(1, "A"),
    B(2, "B"),
    C(3, "C");

    private int number;
    private String name;

    Grade(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public static Grade getGrade(int number) {
        if(number==1) return Grade.A;
        else if(number==2) return Grade.B;
        else return Grade.C;
    }

    public static Grade getGrade(String name) {
        if(name.equals("A")) return Grade.A;
        else if(name.equals("B")) return Grade.B;
        else return Grade.C;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
