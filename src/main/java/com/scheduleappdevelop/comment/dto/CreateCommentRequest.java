package com.scheduleappdevelop.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequest {
    // 댓글 생성을 위한 요청 정보 (댓글 내용)
    @NotBlank @Size(max = 200, message = "댓글 내용은 200자 이하여야 합니다.")
    private String content;
}
