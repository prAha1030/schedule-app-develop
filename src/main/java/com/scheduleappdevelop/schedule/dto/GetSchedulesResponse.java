package com.scheduleappdevelop.schedule.dto;

import lombok.Getter;

@Getter
public class GetSchedulesResponse {
    // 일정 전체 조회 시 응답 정보 (일정 식별 번호, 일정 제목, 유저 식별 번호, 유저명)
    private final Long scheduleId;
    private final String title;
    private final Long userId;
    private final String username;

    public GetSchedulesResponse(
            Long scheduleId, String title, Long userId, String username) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.userId = userId;
        this.username = username;
    }
}
