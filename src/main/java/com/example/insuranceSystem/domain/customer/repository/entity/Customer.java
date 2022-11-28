package com.example.insuranceSystem.domain.customer.repository.entity;

import com.example.insuranceSystem.domain.common.entity.Address;
import com.example.insuranceSystem.domain.common.entity.DateBaseEntity;
import com.example.insuranceSystem.domain.common.entity.EmployeeCustomer;
import com.example.insuranceSystem.domain.contract.repository.entity.Contract;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.KindOfInsurance;
import com.example.insuranceSystem.global.enumerations.KindOfJob;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@Getter
@Entity
public class Customer extends DateBaseEntity {
    @Id @GeneratedValue
    @Column(name = "customer_id")
    private Long id;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String ssn;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private KindOfInsurance kindOfInsurance;

    @Enumerated(EnumType.STRING)
    private KindOfJob kindOfJob;

    @OneToMany(mappedBy = "customer", cascade = ALL)
    private List<Contract> contracts = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = ALL)
    private List<EmployeeCustomer> employeeCustomer = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "health_information_id")
    private HealthInformation healthInformation;

    @Builder
    public Customer(Long id,
                    String password,
                    String name,
                    String email, String address, String detailAddress, String zipcode, String phoneNumber, KindOfInsurance kindOfInsurance, KindOfJob kindOfJob, String ssn, HealthInformation healthInformation) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = new Address(address, detailAddress, zipcode);
        this.phoneNumber = phoneNumber;
        this.kindOfInsurance = kindOfInsurance;
        this.kindOfJob = kindOfJob;
        this.ssn = ssn;
        this.healthInformation = healthInformation;
    }
}
