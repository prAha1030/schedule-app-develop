package com.scheduleappdevelop.schedule.controller;

import com.scheduleappdevelop.schedule.dto.CreateScheduleRequest;
import com.scheduleappdevelop.schedule.dto.CreateScheduleResponse;
import com.scheduleappdevelop.schedule.dto.GetSchedulesResponse;
import com.scheduleappdevelop.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(
            @RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    // 일정 전체 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<GetSchedulesResponse>> getSchedules() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.find());
    }
}
