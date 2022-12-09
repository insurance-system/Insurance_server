package com.example.insuranceSystem.domain.customerService.web.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CustomerLoginResponse {
    private Long id;
    private String kindOfRole;

    public CustomerLoginResponse(Long id, String kindOfRole) {
        this.id = id;
        this.kindOfRole = kindOfRole;
    }
}
