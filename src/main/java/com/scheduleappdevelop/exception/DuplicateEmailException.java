package com.scheduleappdevelop.exception;

import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends ServiceException {
    public DuplicateEmailException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
