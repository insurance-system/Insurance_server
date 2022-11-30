package com.example.insuranceSystem.domain.employeeService.web.dto.request;

import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import com.example.insuranceSystem.domain.customerService.repository.entity.HealthInformation;
import com.example.insuranceSystem.domain.insurance.repository.entity.enumeration.KindOfInsurance;
import com.example.insuranceSystem.global.enumerations.Grade;
import com.example.insuranceSystem.global.enumerations.KindOfJob;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@ApiModel("고객 회원가입")
public class JoinCustomerRequest {

    @ApiModelProperty(value = "아이디", example = "11111", required = false)
    private Long customerId;
    @ApiModelProperty(value = "비밀번호", example = "pw", required = true)
    private String password;
    @ApiModelProperty(value = "이름", example = "고객님", required = true)
    private String name;
    @ApiModelProperty(value = "주소", example = "서울시 서대문구 거북골로 34", required = true)
    private String address;
    @ApiModelProperty(value = "상세주소", example = "명지대학교 S1350", required = true)
    private String detailAddress;
    @ApiModelProperty(value = "우편번호", example = "12345", required = true)
    private String zipcode;
    @ApiModelProperty(value = "이메일", example = "mju@mju.ac.kr", required = true)
    private String email;
    @ApiModelProperty(value = "전화번호", example = "010-1212-8282", required = true)
    private String phoneNumber;
    @ApiModelProperty(value = "직업", example = "student", required = true)
    private String kindOfJob;
    @ApiModelProperty(value = "관심보험", example = "LIFE", required = true)
    private String kindOfInsurance;
    @ApiModelProperty(value = "주민등록번호", example = "111111-111111", required = true)
    private String ssn;
    @ApiModelProperty(value = "암", example = "A", required = true)
    private String cancer;
    @ApiModelProperty(value = "흡연", example = "A", required = true)
    private String smoke;
    @ApiModelProperty(value = "음주", example = "A", required = true)
    private String alcohol;


    public HealthInformation toHealthInformationEntity() {
        return HealthInformation.builder()
                .smoke(Grade.getGrade(this.smoke))
                .alcohol(Grade.getGrade(this.alcohol))
                .cancer(Grade.getGrade(this.cancer))
                .build();
    }

    public Customer toCustomerEntity(HealthInformation healthInformation) {
        return Customer.builder()
                .password(this.password)
                .name(this.name)
                .address(this.address)
                .detailAddress(this.detailAddress)
                .zipcode(this.zipcode)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .kindOfInsurance(KindOfInsurance.getKindOfInsuranceBy(this.kindOfInsurance))
                .kindOfJob(KindOfJob.getKindOfJobBy(this.kindOfJob))
                .ssn(this.ssn)
                .healthInformation(healthInformation)
                .build();
    }
}
