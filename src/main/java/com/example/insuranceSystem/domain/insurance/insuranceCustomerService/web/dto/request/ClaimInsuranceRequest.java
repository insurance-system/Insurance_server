package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.request;

import com.example.insuranceSystem.domain.common.entity.InsuranceClaim;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "보험금 청구 요청 DTO")
@NoArgsConstructor
@Data
public class ClaimInsuranceRequest {

    @ApiModelProperty(value="보험 ID", example = "1")
    private Long insuranceId;

    @ApiModelProperty(value="보험 ID", example = "**한 이유로 보함금을 청구합니다.")
    private String claimContent;

    @ApiModelProperty(value="보험 청구비", example = "250000")
    private int claimCost;

    public InsuranceClaim toEntity() {
        return InsuranceClaim.builder().claimContent(this.claimContent).claimCost(this.claimCost).build();
    }
}
