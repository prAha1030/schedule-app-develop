package com.scheduleappdevelop.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetOneUserResponse {
    // 유저 단건 조회 시 응답 정보
    // (유저 식별 번호, 유저명, 이메일, 작성일, 수정일)
    private final Long id;
    private final String name;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public GetOneUserResponse(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
