package com.scheduleappdevelop.schedule.controller;

import com.scheduleappdevelop.schedule.dto.*;
import com.scheduleappdevelop.schedule.service.ScheduleService;
import com.scheduleappdevelop.user.dto.SessionUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 유저의 일정 생성
    @PostMapping("/users/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @Valid @RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(sessionUser.getId(), request));
    }

    // 일정 전체 조회
    @GetMapping("/users/schedules")
    public ResponseEntity<List<GetSchedulesResponse>> getSchedules() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.find());
    }

    // 일정 단건 조회
    @GetMapping("/users/schedules/{scheduleId}")
    public ResponseEntity<GetOneScheduleResponse> getOneSchedule(
            @PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(scheduleId));
    }

    // 유저의 일정 수정
    @PutMapping("/users/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @PathVariable Long scheduleId,
            @Valid @RequestBody UpdateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(sessionUser.getId(), scheduleId, request));
    }

    // 유저의 일정 삭제
    @DeleteMapping("/users/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @PathVariable Long scheduleId) {
        scheduleService.delete(sessionUser.getId(), scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
