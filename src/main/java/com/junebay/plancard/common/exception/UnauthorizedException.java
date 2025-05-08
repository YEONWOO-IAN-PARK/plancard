package com.junebay.plancard.common.exception;

import com.junebay.plancard.common.enums.StatusCode;

/**
 * @author : IAN
 * @date : 2025-04-24
 * @description : UNAUTHORIZED(401)에 대한 예외 클래스
 */
public class UnauthorizedException extends BaseException {
    public UnauthorizedException(StatusCode err) {
        super(err);
    }
}
