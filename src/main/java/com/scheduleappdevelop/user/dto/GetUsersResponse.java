package com.scheduleappdevelop.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetUsersResponse {
    // 유저 전체 조회 시 응답 정보 (유저 식별 번호, 유저명, 작성일, 수정일)
    private final Long id;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public GetUsersResponse(Long id, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
