package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.request;

import com.example.insuranceSystem.domain.common.repository.entity.IncidentLog;
import com.example.insuranceSystem.domain.common.repository.entity.enumerations.IncidentCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@ApiModel(value = "사고 발생 정보 요청 DTO")
public class IncidentRequest {

    @ApiModelProperty(value="사고 발생 시간", example = "2022-12-15 17:09")
    private String incidentDate;

    @ApiModelProperty(value="차 번호.", example = "42하-3023")
    private String carNumber;

    @ApiModelProperty(value="사고 발생 장소.", example = "서울 마포구 합정동 양화대로")
    private String incidentSite;

    @ApiModelProperty(value="사고 발생자 통화 가능 번호.", example = "01012345678")
    private String incidentPhoneNumber;

    @ApiModelProperty(value="carToCar(차대차), carToMan(차대인), solo(단독사고)", example = "solo")
    private String incidentCategory;

    public IncidentLog toEntity() {
        return IncidentLog.builder()
                .carNumber(this.carNumber)
                .incidentDate(this.incidentDate)
                .incidentSite(this.incidentSite)
                .incidentPhoneNumber(this.incidentPhoneNumber)
                .incidentCategory(IncidentCategory.getIncidentCategoryByName(this.incidentCategory))
                .build();
    }
}
