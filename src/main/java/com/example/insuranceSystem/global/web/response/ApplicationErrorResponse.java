package com.example.insuranceSystem.global.web.response;

import com.example.insuranceSystem.global.exception.ApplicationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationErrorResponse<T>{
    private boolean isSuccess;
    private int httpCode;
    private String errorCode;
    private LocalDateTime localDateTime;
    private HttpStatus httpStatus;
    private String message;

    public <T> ApplicationErrorResponse(String message, List<T> asList) {

    }


    public static <T> ApplicationErrorResponse<T> error(ApplicationException e){
        return (ApplicationErrorResponse<T>) ApplicationErrorResponse.builder()
                .isSuccess(false)
                .httpCode(e.getHttpStatus().value())
                .errorCode(e.getErrorCode())
                .localDateTime(LocalDateTime.now())
                .httpStatus(e.getHttpStatus())
                .message(e.getMessage())
                .build();
    }

}
