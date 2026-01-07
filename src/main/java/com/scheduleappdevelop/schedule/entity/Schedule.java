package com.scheduleappdevelop.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {
    // 일정 식별 번호, 작성자명, 일정 제목, 일정 내용
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String title;
    private String content;

    // 식별 번호를 제외한 속성을 지닌 생성자
    public Schedule(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }

    // 수정을 위한 세터
    public void update(String title) {
        this.title = title;
    }
}
