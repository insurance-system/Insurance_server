package com.example.insuranceSystem.domain.employee.repository.entity;

import com.example.insuranceSystem.domain.common.entity.Address;
import com.example.insuranceSystem.domain.contract.repository.entity.Contract;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Long employeeId;

    private String password;
    private String name;
    private String email;
    private String phoneNumber;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id")
    private Department department;

    @OneToMany(mappedBy = "chargeOfEmployee", cascade = ALL)
    private List<Contract> contracts = new ArrayList<>();




}
