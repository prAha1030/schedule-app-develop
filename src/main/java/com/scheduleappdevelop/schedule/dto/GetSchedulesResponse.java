package com.scheduleappdevelop.schedule.dto;

import lombok.Getter;

@Getter
public class GetSchedulesResponse {
    // 일정 전체 조회 시 응답 정보 (일정 식별 번호, 일정 제목)
    private final Long scheduleId;
    private final String title;

    public GetSchedulesResponse(
            Long scheduleId, String title) {
        this.scheduleId = scheduleId;
        this.title = title;
    }
}
