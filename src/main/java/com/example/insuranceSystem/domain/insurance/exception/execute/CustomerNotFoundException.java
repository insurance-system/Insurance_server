package com.example.insuranceSystem.domain.insurance.exception.execute;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(Long userId) {
        //TODO
        System.out.println("해당하는 id의 user가 없습니다.");
    }
}
