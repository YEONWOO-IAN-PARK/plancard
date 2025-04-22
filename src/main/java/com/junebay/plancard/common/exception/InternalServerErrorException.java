package com.junebay.plancard.common.exception;

/**
 * @author : IAN
 * @date : 2025-04-22
 * @description : INTERNAL_SERVER_ERROR(500)에 대한 예외 클래스
 */
public class InternalServerErrorException extends BaseException {

    public InternalServerErrorException(String message, int status, String details) {
        super(message, status, details);
    }
}
