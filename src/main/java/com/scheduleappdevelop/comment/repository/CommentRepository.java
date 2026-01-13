package com.scheduleappdevelop.comment.repository;

import com.scheduleappdevelop.comment.entity.Comment;
import com.scheduleappdevelop.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByScheduleOrderByUpdatedAtDesc(Schedule schedule);

    void deleteByScheduleId(Long scheduleId);

    Collection<Object> findBySchedule(Schedule p);

    void deleteByScheduleUserId(Long userId);
}
