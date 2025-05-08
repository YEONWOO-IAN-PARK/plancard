package com.junebay.plancard.common.exception;

import com.junebay.plancard.common.enums.StatusCode;

/**
 * @author : IAN
 * @date : 2025-04-24
 * @description : GATEWAY_TIMEOUT(504)에 대한 예외 클래스
 */
public class GatewayTimeoutException extends BaseException {
    public GatewayTimeoutException(StatusCode err) {
        super(err);
    }
}
