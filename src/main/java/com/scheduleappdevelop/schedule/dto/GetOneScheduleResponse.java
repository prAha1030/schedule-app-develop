package com.scheduleappdevelop.schedule.dto;

import com.scheduleappdevelop.comment.dto.GetCommentsResponse;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetOneScheduleResponse {
    // 일정 단건 조회 시 응답 정보
    // (일정 식별 번호, 일정 제목, 일정 내용, 작성일, 수정일, 댓글들)
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<GetCommentsResponse> commentList;

    public GetOneScheduleResponse(
            Long id, String title, String content,
            LocalDateTime createdAt, LocalDateTime updatedAt, List<GetCommentsResponse> commentList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.commentList = commentList;
    }
}
