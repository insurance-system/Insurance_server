package com.example.insuranceSystem.domain.insurance.insuranceCustomerService.web.dto.response;

import com.example.insuranceSystem.domain.common.entity.EmployeeCustomer;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConsultInfoResponse {
    private Long consultId;//EmployeeCustomer_id
    private String employeeName;//상담사 이름
    private String consultDate;//상담 진행 날짜
    private int satisfaction;//만족도
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
