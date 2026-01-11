package com.scheduleappdevelop.exception;

import org.springframework.http.HttpStatus;

public class PageLessZeroOrSizeLessOneException extends ServiceException {
    public PageLessZeroOrSizeLessOneException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
