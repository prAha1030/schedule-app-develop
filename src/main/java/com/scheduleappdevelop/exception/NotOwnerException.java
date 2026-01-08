package com.scheduleappdevelop.exception;

import org.springframework.http.HttpStatus;

public class NotOwnerException extends ServiceException {
    // 다른 유저의 일정 수정 및 삭제 시 발생하는 예외
    public NotOwnerException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
