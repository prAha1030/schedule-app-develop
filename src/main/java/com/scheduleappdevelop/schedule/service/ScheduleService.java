package com.scheduleappdevelop.schedule.service;

import com.scheduleappdevelop.exception.NotOwnerException;
import com.scheduleappdevelop.exception.ScheduleNotFoundException;
import com.scheduleappdevelop.exception.UserNotFoundException;
import com.scheduleappdevelop.schedule.dto.*;
import com.scheduleappdevelop.schedule.entity.Schedule;
import com.scheduleappdevelop.schedule.repository.ScheduleRepository;
import com.scheduleappdevelop.user.entity.User;
import com.scheduleappdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 유저의 일정 생성 요청 -> 응답으로 변환
    @Transactional
    public CreateScheduleResponse save(Long userId, CreateScheduleRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 유저입니다.")
        );
        Schedule schedule = new Schedule(
                user,
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
                        s.getTitle()
                )).toList();
    }

    // 일정 단건 조회 요청 -> 식별 번호에 따른 일정 응답
    @Transactional(readOnly = true)
    public GetOneScheduleResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );
        return new GetOneScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    // 일정 수정 요청 -> 응답으로 변환
    @Transactional
    public UpdateScheduleResponse update(Long userId, Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );
        if (!schedule.getId().equals(userId)) {
            throw new NotOwnerException("다른 유저의 일정을 수정할 수 없습니다.");
        }
        schedule.update(request.getTitle());
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle()
        );
    }

    // 일정 삭제 요청 -> 식별 번호 기준으로 선별한 일정 삭제
    @Transactional
    public void delete(Long userId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );
        if (!schedule.getId().equals(userId)) {
            throw new NotOwnerException("다른 유저의 일정을 삭제할 수 없습니다.");
        }
        scheduleRepository.deleteById(scheduleId);
    }
}
