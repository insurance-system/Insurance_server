package com.example.insuranceSystem.domain.employeeService.repository.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class Lecture {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lecture_id")
    private Long lectureId;

    private String lectureName;
    private String lectureUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Employee employee;

    public Lecture(String lectureName, String lectureUrl, Employee employee) {
        this.lectureName = lectureName;
        this.lectureUrl = lectureUrl;
        this.employee = employee;
    }
}
