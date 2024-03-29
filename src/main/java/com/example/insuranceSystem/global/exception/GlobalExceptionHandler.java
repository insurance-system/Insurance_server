package com.example.insuranceSystem.global.exception;


import com.example.insuranceSystem.global.web.response.ApiErrorResponse;
import com.example.insuranceSystem.global.web.response.ApplicationErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String LOG_FORMAT ="Class : {}, CODE : {}, Message : {}";
    private static final String INTERNAL_SERVER_ERROR_CODE = "S0001";


    @ExceptionHandler(ApplicationException.class)
    public ApplicationErrorResponse<Void> applicationException(ApplicationException e) {
        String errorCode = e.getErrorCode();

        log.warn(
                LOG_FORMAT,
                e.getClass().getSimpleName(),
                errorCode,
                e.getMessage()
        );
        return ApplicationErrorResponse.error(e);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> runtimeException(RuntimeException e) {
        log.error(
                LOG_FORMAT,
                e.getClass().getSimpleName(),
                INTERNAL_SERVER_ERROR_CODE,
                e.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiErrorResponse(INTERNAL_SERVER_ERROR_CODE, Arrays.asList("런타임 에러가 발생했습니다.")));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e){
        String errorCode = requireNonNull(e.getFieldError()).getDefaultMessage();
        ApiErrorResponse exceptionResponse = new ApiErrorResponse("Valid", Arrays.asList(errorCode));
        log.warn(LOG_FORMAT, e.getClass().getSimpleName(), errorCode, "@Valid");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(exceptionResponse);
    }


}
