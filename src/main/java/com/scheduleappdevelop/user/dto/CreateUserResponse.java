package com.scheduleappdevelop.user.dto;

import lombok.Getter;

@Getter
public class CreateUserResponse {
    // 유저 생성 시 응답 정보 (유저 식별 번호, 유저명)
    private final Long id;
    private final String name;

    public CreateUserResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
