package com.example.insuranceSystem.domain.insurance.insuranceEmployeeService.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LectureRequest {
    private String lectureName;
    private String lectureUrl;
}

