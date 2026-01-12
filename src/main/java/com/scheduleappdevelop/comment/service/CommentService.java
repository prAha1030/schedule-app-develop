package com.scheduleappdevelop.comment.service;

import com.scheduleappdevelop.comment.dto.CreateCommentRequest;
import com.scheduleappdevelop.comment.dto.CreateCommentResponse;
import com.scheduleappdevelop.comment.entity.Comment;
import com.scheduleappdevelop.comment.repository.CommentRepository;
import com.scheduleappdevelop.exception.ScheduleNotFoundException;
import com.scheduleappdevelop.schedule.entity.Schedule;
import com.scheduleappdevelop.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    // 댓글 생성 요청 -> 응답 반환
    public CreateCommentResponse save(Long scheduleId, CreateCommentRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );
        Comment comment = new Comment(schedule, request.getContent());
        Comment savedComment = commentRepository.save(comment);
        return new CreateCommentResponse(
                savedComment.getId(),
                savedComment.getContent(),
                savedComment.getCreatedAt(),
                savedComment.getUpdatedAt()
        );
    }
}
