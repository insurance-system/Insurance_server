package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "상담 정보 응답 DTO")
@NoArgsConstructor
@Data
public class JoinInsuranceRequest {

    @ApiModelProperty(value="보험 ID", example = "1")
    private Long insuranceId;
}
