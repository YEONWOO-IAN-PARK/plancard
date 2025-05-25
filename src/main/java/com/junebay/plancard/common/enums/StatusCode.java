package com.junebay.plancard.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : IAN
 * @date : 2025-04-24
 * @description : 모든 예외에 대한 사용자 정의 Enum
 * @TODO : 추후 다국어지원을 위해 Enum의 인자들을 프로퍼티로 관리할 수 있음
 * @caution : 현재 message, status 필드는 사용되고 있지 않지만 추후 사용자 정의 Enum을 더욱 유연하게 사용할때, ResponseEntity 를 반환하는 GlobalExceptionHandler 에서 변경 가능
 */
@Getter
@AllArgsConstructor
public enum StatusCode {

    /* 2xx */
    OK("OK", 200, "요청이 성공적으로 되었습니다."),
    CREATED("Created", 201, "새로운 리소스가 생성되었습니다."),
    NO_CONTENT("No Content", 204, "요청이 성공했지만 반환할 데이터가 없습니다."),

    /* 4xx */
    BAD_REQUEST("Bad Request", 400, "요청하신 데이터 타입이 맞지 않습니다."),
    UNAUTHORIZED("Unauthorized", 401, "인증 토큰이 만료되었습니다."),
    FORBIDDEN("Forbidden", 403, "해당 리소스에 대한 접근 권한이 없습니다."),
    NOT_FOUND("Not Found", 404, "요청하신 리소스를 찾을 수 없습니다."),

    /* 5xx */
    INTERNAL_SERVER_ERROR("Internal Server Error", 500, "예상치 못한 에러가 발생했습니다."),
    GATEWAY_TIMEOUT("Gateway Timeout", 504,"서버의 연결이 원활하지 않습니다.");

    private final String message;
    private final int status;
    private final String details;

}
