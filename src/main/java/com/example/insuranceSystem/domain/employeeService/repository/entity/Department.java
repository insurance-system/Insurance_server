package com.example.insuranceSystem.domain.employeeService.repository.entity;

import com.example.insuranceSystem.domain.common.repository.entity.DateBaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class Department extends DateBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id")
    private Long departmentId;

    @OneToMany(mappedBy = "department", cascade = ALL)
    private List<Employee> employees;

    private String departmentName;
}
