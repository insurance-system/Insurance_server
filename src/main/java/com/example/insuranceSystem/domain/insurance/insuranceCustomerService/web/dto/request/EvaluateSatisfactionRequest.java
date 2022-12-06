package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EvaluateSatisfactionRequest {
    private Long consultId;
    private int satisfaction;
}
