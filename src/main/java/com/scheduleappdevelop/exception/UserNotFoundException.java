package com.scheduleappdevelop.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ServiceException{
    // 존재하지 않는 유저를 찾을 때 발생하는 예외
    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
