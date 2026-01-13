package com.scheduleappdevelop.user.dto;

import lombok.Getter;

@Getter
public class SessionUser {
    // 세션 저장 정보 (유저 식별 번호, 유저명, 이메일, 비밀번호)
    private final Long id;
    private final String name;
    private final String email;
    private final String password;

    public SessionUser(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
