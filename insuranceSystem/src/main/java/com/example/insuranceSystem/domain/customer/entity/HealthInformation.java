package com.example.insuranceSystem.domain.customer.entity;

import com.example.insuranceSystem.global.enumerations.Grade;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@Getter
@Entity
public class HealthInformation {
    @Id @GeneratedValue
    private int id;
    private Grade cancer;
    private Grade smoke;
    private Grade alcohol;
}
