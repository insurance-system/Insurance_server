package com.example.insuranceSystem.domain.employeeService.repository.entity;

import com.example.insuranceSystem.domain.common.repository.entity.Address;
import com.example.insuranceSystem.domain.common.repository.entity.DateBaseEntity;
import com.example.insuranceSystem.domain.common.repository.entity.EmployeeCustomer;
import com.example.insuranceSystem.domain.common.repository.entity.IncidentLog;
import com.example.insuranceSystem.domain.contract.repository.entity.Contract;
import com.example.insuranceSystem.global.enumerations.KindOfRole;
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
public class Employee extends DateBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Long employeeId;

    private String password;
    private String name;
    private String email;
    private String phoneNumber;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private KindOfRole kindOfRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id")
    private Department department;

    @OneToMany(mappedBy = "chargeOfEmployee", cascade = ALL)
    private List<Contract> contracts = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = ALL)
    private List<IncidentLog> incidentLog = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = ALL)
    private List<EmployeeCustomer> employeeCustomerList = new ArrayList<>();

    public void addEmployeeCustomer(EmployeeCustomer employeeCustomer) {
        this.employeeCustomerList.add(employeeCustomer);
    }

    public void addIncidentLog(IncidentLog incidentLog) {
        this.incidentLog.add(incidentLog);
    }
}
