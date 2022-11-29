package com.example.insuranceSystem.domain.customerService.exception;

public class UserEmailNotFoundException extends RuntimeException{
    public UserEmailNotFoundException(String email) {
        System.out.println(email+" 해당 이메일로 저장된 유저를 찾을 수 없습니다.");
    }
}