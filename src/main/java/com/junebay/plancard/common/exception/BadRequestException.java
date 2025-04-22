package com.junebay.plancard.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author : IAN
 * @date : 2025-04-22
 * @description : BAD_REQUEST(400)에 대한 예외 클래스
 */
public class BadRequestException extends BaseException {

    public BadRequestException(String message, int statusCode, String details) {
        super(message, statusCode, details);
    }
}
