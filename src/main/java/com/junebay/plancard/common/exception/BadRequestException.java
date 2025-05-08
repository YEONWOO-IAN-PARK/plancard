package com.junebay.plancard.common.exception;

import com.junebay.plancard.common.enums.StatusCode;

/**
 * @author : IAN
 * @date : 2025-04-22
 * @description : BAD_REQUEST(400)에 대한 예외 클래스
 */
public class BadRequestException extends BaseException {

    public BadRequestException(StatusCode err) {
        super(err);
    }
}
