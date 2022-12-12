package com.example.insuranceSystem.domain.employeeService.web.dto.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@ApiModel("사원 로그인")
public class LoginEmployeeRequest {
    @ApiModelProperty(value = "이메일", example = "mju@mju.ac.kr", required = true)
    private String email;

    @ApiModelProperty(value = "비밀번호", example = "pw", required = true)
    private String password;
}
