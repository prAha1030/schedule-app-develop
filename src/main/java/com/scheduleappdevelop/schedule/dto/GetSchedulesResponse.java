package com.scheduleappdevelop.schedule.dto;

import lombok.Getter;

@Getter
public class GetSchedulesResponse {
    // 일정 전체 조회 시 응답 정보 (일정 식별 번호, 작성자명, 일정 제목)
    private final Long id;
    private final String username;
    private final String title;

    public GetSchedulesResponse(Long id, String username, String title) {
        this.id = id;
        this.username = username;
        this.title = title;
    }
}
