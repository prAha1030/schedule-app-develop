package com.scheduleappdevelop.user.dto;

import lombok.Getter;

@Getter
public class GetUsersResponse {
    // 유저 전체 조회 시 응답 정보 (유저 식별 번호, 유저명)
    private final Long id;
    private final String name;

    public GetUsersResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
