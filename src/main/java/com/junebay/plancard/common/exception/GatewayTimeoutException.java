package com.junebay.plancard.common.exception;

import com.junebay.plancard.common.enums.ErrorCode;
import lombok.Getter;

/**
 * @author : IAN
 * @date : 2025-04-24
 * @description : GATEWAY_TIMEOUT(504)에 대한 예외 클래스
 */
public class GatewayTimeoutException extends BaseException {
    public GatewayTimeoutException(ErrorCode err) {
        super(err);
    }
}
