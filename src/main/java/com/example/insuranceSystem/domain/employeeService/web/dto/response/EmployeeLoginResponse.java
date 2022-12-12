package com.example.insuranceSystem.domain.employeeService.web.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EmployeeLoginResponse {
    private Long id;
    private String name;
    private String kindOfRole;

    public EmployeeLoginResponse(Long id, String name, String kindOfRole) {
        this.id = id;
        this.name = name;
        this.kindOfRole = kindOfRole;
    }
}
