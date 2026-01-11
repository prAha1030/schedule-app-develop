package com.scheduleappdevelop.comment.entity;

import com.scheduleappdevelop.schedule.entity.BaseEntity;
import com.scheduleappdevelop.schedule.entity.Schedule;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLRestriction("deleted_at is NULL")
@SQLDelete(sql = "UPDATE comments SET deleted_at = NOW() WHERE id = ?")
public class Comment extends BaseEntity {
    // 댓글 식별 번호, 일정 식별 번호, 유저 식별 번호, 댓글 내용, 작성일, 수정일
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;
    private String content;
    private LocalDateTime deletedAt;

    // 댓글 식별 번호를 제외한 생성자
    public Comment(Schedule schedule, String content) {
        this.schedule = schedule;
        this.content = content;
    }
}
