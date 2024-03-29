package com.example.insuranceSystem.global.web.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ApiErrorResponse {

    private boolean isSuccess;
    private LocalDateTime timeStamp;
    private String errorCode;
    private List<String> message;

    public ApiErrorResponse(String errorCode, List<String> message) {
        this.isSuccess = false;
        this.timeStamp = LocalDateTime.now().withNano(0);
        this.errorCode = errorCode;
        this.message = message;
    }

}
