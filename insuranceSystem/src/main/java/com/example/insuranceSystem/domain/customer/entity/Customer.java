package com.example.insuranceSystem.domain.customer.entity;

import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.KindOfInsurance;
import com.example.insuranceSystem.global.enumerations.KindOfJob;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@Getter
@Entity
public class Customer {
    @Id @GeneratedValue
    private Long id;
    private String password;
    private String name;
    private String email;
    private String address;
    private String detailAddress;
    private String zipcode;
    private String phoneNumber;
    private KindOfInsurance kindOfInsurance;
    private KindOfJob kindOfJob;
    private String ssn;
    private String healthInformationId;//TODO 외래키로 HealthInforamtion과 연결 N : 1 관계

}
