package com.scheduleappdevelop.user.entity;

import com.scheduleappdevelop.schedule.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    // 유저 식별 번호, 유저명, 이메일, 작성일, 수정일
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    // 유저 식별 번호를 제외한 속성을 지닌 생성자
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // 유저 수정을 위한 세터
    public void update(String name) {
        this.name = name;
    }
}
