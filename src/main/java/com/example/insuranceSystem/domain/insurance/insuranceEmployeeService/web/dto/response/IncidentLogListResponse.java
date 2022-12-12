package com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class IncidentLogListResponse {
    private Long incidentLogId;
    private String customerName;
    private String incidentPhoneNumber;
    private String incidentDate;
    private String incidentSite;
    private String carNumber;
    private String incidentCategory;
}
