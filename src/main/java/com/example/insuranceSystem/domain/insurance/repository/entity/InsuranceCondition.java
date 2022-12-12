package com.example.insuranceSystem.domain.insurance.repository.entity;

import com.example.insuranceSystem.domain.common.entity.DateBaseEntity;
import com.example.insuranceSystem.global.enumerations.Grade;
import lombok.*;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@Embeddable
@Getter
@Setter(AccessLevel.PROTECTED)
public class InsuranceCondition{

    private int maxAge;
    private int minAge;
    @Enumerated(EnumType.STRING)
    private Grade smoke;
    @Enumerated(EnumType.STRING)
    private Grade alcohol;
    @Enumerated(EnumType.STRING)
    private Grade cancer;

    @Builder
    public InsuranceCondition(int maxAge,
                              int minAge,
                              Grade smoke,
                              Grade alcohol,
                              Grade cancer) {
        this.maxAge = maxAge;
        this.minAge = minAge;
        this.smoke = smoke;
        this.alcohol = alcohol;
        this.cancer = cancer;
    }
}
