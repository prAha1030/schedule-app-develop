package com.scheduleappdevelop.schedule.service;

import com.scheduleappdevelop.schedule.dto.CreateScheduleRequest;
import com.scheduleappdevelop.schedule.dto.CreateScheduleResponse;
import com.scheduleappdevelop.schedule.dto.GetSchedulesResponse;
import com.scheduleappdevelop.schedule.entity.Schedule;
import com.scheduleappdevelop.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    // 일정 생성 요청 -> 응답으로 변환
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getUsername(),
                request.getTitle(),
                request.getContent()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(savedSchedule.getId(), savedSchedule.getTitle());
    }

    // 일정 전체 조회 요청 -> 전체 목록 응답
    @Transactional(readOnly = true)
    public List<GetSchedulesResponse> find() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(s -> new GetSchedulesResponse(
                        s.getId(),
                        s.getUsername(),
                        s.getTitle()
                )).toList();
    }
}
