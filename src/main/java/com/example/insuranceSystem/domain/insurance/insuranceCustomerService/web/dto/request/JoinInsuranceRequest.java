package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "보험 가입 요청 DTO")
@NoArgsConstructor
@Data
public class JoinInsuranceRequest {

    @ApiModelProperty(value="보험 ID", example = "1")
    private Long insuranceId;
}
