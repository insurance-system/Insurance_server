package com.example.insuranceSystem.domain.customerService.repository.entity;

import com.example.insuranceSystem.domain.common.entity.DateBaseEntity;
import com.example.insuranceSystem.global.enumerations.Grade;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@Getter
@Entity
public class HealthInformation extends DateBaseEntity {
    @Id @GeneratedValue
    private int id;
    @Enumerated(EnumType.STRING)
    private Grade cancer;
    @Enumerated(EnumType.STRING)
    private Grade smoke;
    @Enumerated(EnumType.STRING)
    private Grade alcohol;

    @Builder
    public HealthInformation(Grade cancer, Grade smoke, Grade alcohol) {
        this.cancer = cancer;
        this.smoke = smoke;
        this.alcohol = alcohol;
    }
}
