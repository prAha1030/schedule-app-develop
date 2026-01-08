package com.scheduleappdevelop.exception;

import org.springframework.http.HttpStatus;

public class ScheduleNotFoundException extends ServiceException {
    // 존재하지 않는 일정을 찾을 때 발생하는 예외
    public ScheduleNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
