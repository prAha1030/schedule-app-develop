package com.scheduleappdevelop.comment.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {
    // 댓글 생성 시 응답 정보 (댓글 식별 번호, 댓글 내용, 작성일, 수정일)
    private final Long id;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CreateCommentResponse(Long id, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
