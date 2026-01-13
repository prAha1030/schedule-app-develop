package com.scheduleappdevelop.exception;

import org.springframework.http.HttpStatus;

public class PageLessZeroOrSizeLessOneException extends ServiceException {
    // 페이지 번호가 0보다 작거나, 페이지 크기가 1보다 작을 때 발생하는 예외
    public PageLessZeroOrSizeLessOneException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
