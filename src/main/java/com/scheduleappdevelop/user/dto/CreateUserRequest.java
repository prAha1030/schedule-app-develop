package com.scheduleappdevelop.user.dto;

import lombok.Getter;

@Getter
public class CreateUserRequest {
    // 유저 생성을 위한 요청 정보 (유저명, 이메일)
    private String name;
    private String email;
}
