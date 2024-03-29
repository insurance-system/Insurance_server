package com.example.insuranceSystem.domain.common.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class) // 이벤트 기반으로 동작한다!
@Getter
@MappedSuperclass
public class DateBaseEntity implements Serializable {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    //    @JsonDeserialize(using = LocalDateDeserializer.class)
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @PrePersist//Persist하기 전에 호출
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        createdDate = now;
        lastModifiedDate = now;
    }

    @PreUpdate//Update하기 전에 호출
    public void preUpdate(){
        lastModifiedDate = LocalDateTime.now();
    }

    @Setter
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isEnable = true;
}