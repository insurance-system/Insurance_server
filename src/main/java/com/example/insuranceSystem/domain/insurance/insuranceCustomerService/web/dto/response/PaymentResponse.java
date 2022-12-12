package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response;

import com.example.insuranceSystem.domain.common.entity.Payment;
import lombok.Getter;


@Getter
public class PaymentResponse {
    private Long paymentId;
    private int payCost;
    private String payDate;

    public static PaymentResponse toDto(Payment p){
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.paymentId = p.getId();
        paymentResponse.payDate = p.getCreatedDate().toString();
        paymentResponse.payCost = p.getPayCost();
        return paymentResponse;
    }
}
