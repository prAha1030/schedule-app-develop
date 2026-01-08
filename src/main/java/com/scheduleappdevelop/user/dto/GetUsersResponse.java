package com.scheduleappdevelop.user.dto;

import lombok.Getter;

@Getter
public class GetUsersResponse {
    // 유저 전체 생성 시 응답 정보 (유저 식별 번호, 유저명, 이메일)
    private final Long id;
    private final String name;
    private final String email;

    public GetUsersResponse(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
