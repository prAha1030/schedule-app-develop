package com.scheduleappdevelop.schedule.entity;

import com.scheduleappdevelop.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLRestriction("deleted_at is NULL")
@SQLDelete(sql = "UPDATE schedules SET deleted_at = NOW() WHERE id = ?")
public class Schedule extends BaseEntity {
    // 일정 식별 번호, 댓글, fk 유저, 일정 제목, 일정 내용, 작성일, 수정일, 논리적 삭제 시간
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false, length = 20)
    private String title;
    @Column(nullable = false, length = 200)
    private String content;
    private LocalDateTime deletedAt;

    public Schedule(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    // 수정을 위한 세터
    public void update(String title) {
        this.title = title;
    }
}
