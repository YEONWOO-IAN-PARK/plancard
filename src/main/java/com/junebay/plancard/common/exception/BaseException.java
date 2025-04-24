package com.junebay.plancard.common.exception;

import com.junebay.plancard.common.enums.ErrorCode;
import lombok.Getter;

/**
 * @author : IAN
 * @date : 2025-04-22
 * @description : 기본적인 사용자 정의 예외
 */
@Getter
public class BaseException extends RuntimeException {

    private final int status;
    private final String details;

    public BaseException(ErrorCode err) {
        super(err.getMessage());
        this.status = err.getStatus();
        this.details = err.getDetails();
    }

}
