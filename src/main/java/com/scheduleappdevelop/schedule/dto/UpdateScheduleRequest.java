package com.scheduleappdevelop.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    // 일정 수정을 위한 요청 정보 (일정 제목)
    @NotBlank @Size(max = 20, message = "일정 제목은 20자 이하여야 합니다.")
    private String title;
}
