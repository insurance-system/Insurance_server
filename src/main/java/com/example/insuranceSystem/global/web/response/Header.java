package com.example.insuranceSystem.global.web.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class Header<T>{

    private T data;
    private LocalDateTime transaction_time;
    private HttpStatus status;
    private String description;
    private int statusCode;

    // OK
    public static <T> Header<T> CREATED(@Nullable T data) {
        return (Header<T>) Header.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.OK)
                .data(data)
                .build();
    }

    public static <T> Header<T> OK(@Nullable T data) {
        return (Header<T>) Header.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.OK)
                .data(data)
                .build();
    }

    public static <T> Header<T> OK() {
        return (Header<T>) Header.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.OK)
                .build();
    }

    public static <T> Header<T> BAD_REQUEST(@Nullable String description){
        return (Header<T>) Header.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .description(description)
                .build();
    }

    public static <T> Header<T> BAD_REQUEST(@Nullable T data){
        return (Header<T>) Header.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .data(data)
                .build();
    }

    public static <T> Header<T> FORBIDDEN(){
        return (Header<T>) Header.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN)
                .build();
    }

    public static <T> Header<T> FORBIDDEN(String description){
        return (Header<T>) Header.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN)
                .description(description)
                .build();
    }


    public static <T> Header<T> UNAUTHORIZED(){
        return (Header<T>) Header.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }

    public static <T> Header<T> INTERNAL_SERVER_ERROR(){
        return (Header<T>) Header.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    public static <T> Header<T> INTERNAL_SERVER_ERROR(String description){
        return (Header<T>) Header.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .description(description)
                .build();
    }

    public static <T> Header<T> JWT_EXPIRED(){
        return (Header<T>) Header.builder()
                .transaction_time(LocalDateTime.now())
                .description("JWT_EXPIRED")
                .statusCode(441)
                .build();
    }
}
