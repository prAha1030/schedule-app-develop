package com.scheduleappdevelop.schedule.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleResponse {
    // 일정 수정 시 응답 정보 (일정 식별 번호, 일정 제목)
    private final Long id;
    private final String title;

    public UpdateScheduleResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
