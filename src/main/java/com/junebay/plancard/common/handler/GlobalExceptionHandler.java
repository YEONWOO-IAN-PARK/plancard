package com.junebay.plancard.common.handler;

import com.junebay.plancard.common.dto.ResponseDTO;
import com.junebay.plancard.common.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : IAN
 * @date : 2025-04-22
 * @description : 전역 예외 핸들러 클래스
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ResponseDTO> handleBadRequestException(BaseException ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(ex.getMessage());
        responseDTO.setStatus(ex.getStatusCode());
        responseDTO.setDetails(ex.getDetails());
        return ResponseEntity.badRequest().body(responseDTO);
    }
}
