package com.scheduleappdevelop.exception;

import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends ServiceException {
    // 이메일 중복 시 발생하는 예외
    public DuplicateEmailException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
