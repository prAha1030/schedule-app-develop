package com.scheduleappdevelop.schedule.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    // 일정 생성을 위한 요청 정보 (일정 제목, 일정 내용)
    private String title;
    private String content;
}
