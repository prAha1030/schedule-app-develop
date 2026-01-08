package com.scheduleappdevelop.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserRequest {
    // 유저 수정을 위한 요청 정보 (유저명)
    @NotBlank @Size(max = 10, message = "유저명은 10자 이하여야 합니다.")
    private String name;
}
