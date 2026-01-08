package com.scheduleappdevelop.exception;

import org.springframework.http.HttpStatus;

public class PasswordNotMatchException extends ServiceException {
    // 비밀번호 미일치 시 발생하는 예외
    public PasswordNotMatchException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
