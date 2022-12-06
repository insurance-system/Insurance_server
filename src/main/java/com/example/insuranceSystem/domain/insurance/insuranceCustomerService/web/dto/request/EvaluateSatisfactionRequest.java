package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "상담 정보 응답 DTO")
@NoArgsConstructor
@Data
public class EvaluateSatisfactionRequest {
    @ApiModelProperty(value="상담 내역 리스트에서 제공한 상담 ID", example = "1")
    private Long consultId;
    @ApiModelProperty(value="만족도는 1, 2, 3, 4, 5만 허용한다.", example = "4")
    private int satisfaction;
}
