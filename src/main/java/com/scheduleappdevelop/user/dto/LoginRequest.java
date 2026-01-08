package com.scheduleappdevelop.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {
    // 로그인을 위한 요청 정보 (이메일, 비밀번호)
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
