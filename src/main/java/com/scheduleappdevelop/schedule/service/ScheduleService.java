package com.scheduleappdevelop.schedule.service;

import com.scheduleappdevelop.comment.dto.GetCommentsResponse;
import com.scheduleappdevelop.comment.entity.Comment;
import com.scheduleappdevelop.comment.repository.CommentRepository;
import com.scheduleappdevelop.exception.NotOwnerException;
import com.scheduleappdevelop.exception.PageLessZeroOrSizeLessOneException;
import com.scheduleappdevelop.exception.ScheduleNotFoundException;
import com.scheduleappdevelop.exception.UserNotFoundException;
import com.scheduleappdevelop.schedule.dto.*;
import com.scheduleappdevelop.schedule.entity.Schedule;
import com.scheduleappdevelop.schedule.repository.ScheduleRepository;
import com.scheduleappdevelop.user.entity.User;
import com.scheduleappdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    // 일정 생성 요청 -> 응답 변환
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

    // 일정 전체 조회 요청 -> 응답 변환
    @Transactional(readOnly = true)
    public Page<GetSchedulesResponse> find(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new PageLessZeroOrSizeLessOneException("잘못된 입력 : page가 0보다 작거나 size가 1보다 작습니다.");
        }
        // 요청 정보의 페이지번호, 크기에 해당하는 페이지 (수정일 기준 내림차순)
        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        return scheduleRepository.findAllBy(pageable).map(p -> new GetSchedulesResponse(
                p.getTitle(),
                p.getContent(),
                commentRepository.findBySchedule(p).size(),
                p.getCreatedAt(),
                p.getUpdatedAt(),
                p.getUser().getName()
        ));
    }

    // 일정 단건 조회 요청 -> 응답 변환 (일정 식별 번호 기준)
    @Transactional(readOnly = true)
    public GetOneScheduleResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );
        // 댓글들 수정일 기준으로 내림차순 정렬
        List<Comment> comments = commentRepository.findByScheduleOrderByUpdatedAtDesc(schedule);
        List<GetCommentsResponse> commentsList = comments.stream()
                .map(c -> new GetCommentsResponse(
                        c.getId(),
                        c.getContent(),
                        c.getCreatedAt(),
                        c.getUpdatedAt()
                )).toList();
        return new GetOneScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                commentsList
        );
    }

    // 일정 수정 요청 -> 응답 변환 (일정 식별 번호 기준)
    @Transactional
    public UpdateScheduleResponse update(Long userId, Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );
        if (!schedule.getUser().getId().equals(userId)) {
            throw new NotOwnerException("다른 유저의 일정을 수정할 수 없습니다.");
        }
        schedule.update(request.getTitle());
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle()
        );
    }

    // 일정 삭제 요청 -> 응답 변환 (일정 식별 번호 기준)
    @Transactional
    public void delete(Long userId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );
        if (!schedule.getUser().getId().equals(userId)) {
            throw new NotOwnerException("다른 유저의 일정을 삭제할 수 없습니다.");
        }
        // 댓글 삭제 후 일정 삭제
        commentRepository.deleteByScheduleId(scheduleId);
        scheduleRepository.deleteById(scheduleId);
    }
}
