package com.junebay.plancard.common.exception;

import lombok.Getter;

/**
 * @author : IAN
 * @date : 2025-04-22
 * @description :
 */
@Getter
public class BaseException extends RuntimeException {

    private final String details;

    public BaseException(String details) {
        this.details = details;
    }

}
