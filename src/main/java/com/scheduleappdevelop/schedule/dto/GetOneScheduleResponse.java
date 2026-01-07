package com.scheduleappdevelop.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetOneScheduleResponse {
    // 일정 단건 조회 시 응답 정보
    // (일정 식별 번호, 작성자명, 일정 제목, 일정 내용, 작성일, 수정일)
    private final Long id;
    private final String username;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public GetOneScheduleResponse(
            Long id, String username, String title, String content,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
