package com.example.insuranceSystem.domain.common.repository.entity;

import lombok.*;

import javax.persistence.Embeddable;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Getter
public class Address {
    private String address;
    private String detailAddress;
    private String zipcode;
}
