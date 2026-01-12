package com.scheduleappdevelop.user.entity;

import com.scheduleappdevelop.schedule.entity.BaseEntity;
import com.scheduleappdevelop.schedule.entity.Schedule;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                // 복합 유니크 키 (이메일, 논리적 삭제 시간)
                @UniqueConstraint(
                        name = "uk_email_deleted_at",
                        columnNames = {"email", "deleted_at"}
                )
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLRestriction("deleted_at is NULL")
@SQLDelete(sql = "UPDATE users SET deleted_at = NOW() WHERE id = ?")
public class User extends BaseEntity {
    // 유저 식별 번호, 일정, 유저명, 이메일, 비밀번호, 작성일, 수정일, 논리적 삭제 시간
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();
    @Column(nullable = false, length = 10)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private LocalDateTime deletedAt;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // 유저 수정을 위한 세터
    public void update(String name) {
        this.name = name;
    }
}
