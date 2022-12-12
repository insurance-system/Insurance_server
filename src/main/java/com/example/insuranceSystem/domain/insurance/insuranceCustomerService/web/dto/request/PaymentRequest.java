package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PaymentRequest {
    private Long contractId;
    private int payCost;
}
