package com.example.insuranceSystem.domain.common.repository.entity;

import com.example.insuranceSystem.domain.common.repository.entity.enumerations.IncidentCategory;
import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import com.example.insuranceSystem.domain.employeeService.repository.entity.Employee;
import com.example.insuranceSystem.global.util.date.DateFormatter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
@Entity
public class IncidentLog extends DateBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "incident_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;

    private LocalDateTime incidentDate;

    private String carNumber;

    private String incidentSite;

    private String incidentPhoneNumber;

    @Enumerated(EnumType.STRING)
    private IncidentCategory incidentCategory;

    public IncidentLog(IncidentLog incidentLog, Customer customer) {
        this.employee = incidentLog.employee;
        this.incidentDate = incidentLog.incidentDate;
        this.carNumber = incidentLog.carNumber;
        this.incidentSite = incidentLog.incidentSite;
        this.incidentPhoneNumber = incidentLog.incidentPhoneNumber;
        this.incidentCategory = incidentLog.incidentCategory;
        this.addCustomer(customer);
    }

    @Builder
    public IncidentLog(String incidentDate,
                       String carNumber,
                       String incidentSite,
                       String incidentPhoneNumber,
                       IncidentCategory incidentCategory){
        this.incidentDate = DateFormatter.strToDate(incidentDate);
        this.carNumber = carNumber;
        this.incidentSite = incidentSite;
        this.incidentPhoneNumber = incidentPhoneNumber;
        this.incidentCategory = incidentCategory;
    }

    public void addCustomer(Customer customer) {
        this.customer = customer;
        this.customer.addIncidentLog(this);
    }

    public void addEmployee(Employee employee) {
        this.employee = employee;
        this.employee.addIncidentLog(this);
    }
}