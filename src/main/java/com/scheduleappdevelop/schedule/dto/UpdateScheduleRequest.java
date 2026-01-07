package com.scheduleappdevelop.schedule.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    // 일정 수정을 위한 요청 정보 (일정 제목)
    private String title;
}
