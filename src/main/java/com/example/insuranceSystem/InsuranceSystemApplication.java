package com.example.insuranceSystem;

import com.example.insuranceSystem.global.util.date.DateFormatter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class InsuranceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceSystemApplication.class, args);
		LocalDateTime localDateTime = DateFormatter.strToDate("2022-12-15 17:09");
		System.out.println(DateFormatter.dateToStr(localDateTime));

	}
}
