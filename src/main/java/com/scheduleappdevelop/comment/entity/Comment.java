package com.scheduleappdevelop.comment.entity;

import com.scheduleappdevelop.schedule.entity.BaseEntity;
import com.scheduleappdevelop.schedule.entity.Schedule;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    // 댓글 식별 번호, 일정 식별 번호, 유저 식별 번호, 댓글 내용, 작성일, 수정일
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;
    private String content;
}
