package com.scheduleappdevelop.user.dto;

import lombok.Getter;

@Getter
public class UpdateUserRequest {
    // 유저 수정을 위한 요청 정보 (유저명)
    private String name;
}
