package com.scheduleappdevelop.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateUserRequest {
    // 유저 생성을 위한 요청 정보 (유저명, 이메일, 비밀번호)
    private String name;
    @NotBlank @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;
    @NotBlank @Size(min = 8, max = 20, message = "비밀번호는 8~20자 사이여야 합니다.")
    private String password;
}
