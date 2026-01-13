package com.scheduleappdevelop.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetSchedulesResponse {
    // 일정 전체 조회 시 응답 정보
    // (일정 제목, 일정 내용, 댓글 개수, 작성일, 수정일, 유저명)
    private final String title;
    private final String content;
    private final int commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String userName;

    public GetSchedulesResponse(String title, String content, int commentCount,
                                LocalDateTime createdAt, LocalDateTime updatedAt, String userName) {
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userName = userName;
    }
}
