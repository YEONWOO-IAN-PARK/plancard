package com.junebay.plancard.common.exception;

import com.junebay.plancard.common.enums.StatusCode;

/**
 * @author : IAN
 * @date : 2025-04-24
 * @description : NOT_FOUND(404)에 대한 예외 클래스
 */
public class NotFoundException extends BaseException {
    public NotFoundException(StatusCode err) {
        super(err);
    }
}
