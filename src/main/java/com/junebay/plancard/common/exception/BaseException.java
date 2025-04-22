package com.junebay.plancard.common.exception;

import lombok.Getter;

/**
 * @author : IAN
 * @date : 2025-04-22
 * @description :
 */
@Getter
public class BaseException extends RuntimeException {

    private final String message;
    private final int statusCode;
    private final String details;

    public BaseException(String message, int status, String details) {
        super(message);             // RuntimeException 이 가진 메시지 필드 세팅. ex.getMessage() 시 전달된 메세지를 출력한다.
        this.message = message;
        this.statusCode = status;
        this.details = details;
    }

}
