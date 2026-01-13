package com.scheduleappdevelop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException {
    // 서비스 로직에서 발생하는 예외들의 공통 상위 예외
    private final HttpStatus status;
    public ServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
