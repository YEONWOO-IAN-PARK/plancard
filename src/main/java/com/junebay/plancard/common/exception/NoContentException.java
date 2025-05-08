package com.junebay.plancard.common.exception;

import com.junebay.plancard.common.enums.StatusCode;

/**
 * @author : IAN
 * @date : 2025-04-29
 * @description :
 */
public class NoContentException extends BaseException {

    public NoContentException(StatusCode err) {super(err);}
}
