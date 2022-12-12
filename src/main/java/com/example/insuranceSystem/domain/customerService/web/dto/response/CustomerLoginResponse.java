package com.example.insuranceSystem.domain.customerService.web.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CustomerLoginResponse {
    private Long id;
    private String name;
    private String kindOfRole;

    public CustomerLoginResponse(Long id, String name, String kindOfRole) {
        this.id = id;
        this.name = name;
        this.kindOfRole = kindOfRole;

    }
}
