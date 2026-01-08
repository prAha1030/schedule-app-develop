package com.scheduleappdevelop.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    // 일정 생성을 위한 요청 정보 (일정 제목, 일정 내용)
    @NotBlank @Size(max = 20, message = "일정 제목은 20자 이하여야 합니다.")
    private String title;
    @NotBlank @Size(max = 200, message = "일정 내용은 200자 이하여야 합니다.")
    private String content;
}
