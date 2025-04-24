package com.junebay.plancard.common.exception;

import com.junebay.plancard.common.enums.ErrorCode;
import lombok.Getter;

/**
 * @author : IAN
 * @date : 2025-04-24
 * @description : INTERNAL_SERVER_ERROR(500)에 대한 예외 클래스
 */
public class InternalServerErrorException extends BaseException {
    public InternalServerErrorException(ErrorCode err) {
        super(err);
    }
}
