package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response;

import com.example.insuranceSystem.domain.common.entity.EmployeeCustomer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "상담 정보 응답 DTO")
@NoArgsConstructor
@Getter
public class ConsultInfoResponse {

    @ApiModelProperty(value="상담 ID", example = "1")
    private Long consultId;//EmployeeCustomer_id
    @ApiModelProperty(value="상담 직원 이름", example = "이찬영")
    private String employeeName;//상담사 이름
    @ApiModelProperty(value="상담한 날짜")
    private String consultDate;//상담 진행 날짜
    @ApiModelProperty(value="만족도", example = "5")
    private int satisfaction;//만족도
    @ApiModelProperty(value="Customer가 해당 상담에 대해 평가를 했는지 안했는지의 여부. 평가를 했다면 true", example = "false")
    private boolean hasEvaluate;//평가를 했는지 여부

    public static ConsultInfoResponse toDto(EmployeeCustomer employeeCustomer) {
        ConsultInfoResponse consultInfoResponse = new ConsultInfoResponse();
        consultInfoResponse.consultId = employeeCustomer.getEmpCusId();
        consultInfoResponse.employeeName = employeeCustomer.getEmployee().getName();
        consultInfoResponse.consultDate = employeeCustomer.getCreatedDate().toString();
        consultInfoResponse.satisfaction = employeeCustomer.getSatisfaction();
        consultInfoResponse.hasEvaluate = employeeCustomer.getSatisfaction() != null;
        return consultInfoResponse;
    }
}
