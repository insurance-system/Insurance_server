package com.example.insuranceSystem.domain.employeeService.repository;

import com.example.insuranceSystem.domain.employeeService.repository.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long>  {
}
