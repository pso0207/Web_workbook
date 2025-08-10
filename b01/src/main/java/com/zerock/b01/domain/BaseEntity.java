package com.zerock.b01.domain;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class}) // 엔티티가 추가되거나 변경될 때 자동으로 시간 값을 지정할 수 있음
@Getter
abstract class BaseEntity { //모든 엔티티에 자동으로 시간 기록해주는 기능

    @CreatedDate
    @Column(name = "regdate", updatable = false) // 자바 필드명과 db 컬럼명이 다르게 나와있으면 좋겠을때 사용
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
}
