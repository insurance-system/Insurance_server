package com.example.insuranceSystem.domain.employee.repository.entity;

import javax.persistence.*;

@Entity
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Long employeeId;
}
