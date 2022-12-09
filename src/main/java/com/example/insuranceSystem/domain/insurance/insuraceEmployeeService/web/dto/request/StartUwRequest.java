package com.example.insuranceSystem.domain.insurance.insuraceEmployeeService.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StartUwRequest {
    private Long contractId;
    private String contractStatus;
}
