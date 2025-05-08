package com.junebay.plancard.common.handler;

import com.junebay.plancard.common.dto.ResponseDTO;
import com.junebay.plancard.common.exception.BadRequestException;
import com.junebay.plancard.common.exception.BaseException;
import com.junebay.plancard.common.exception.NoContentException;
import com.junebay.plancard.common.exception.NotFoundException;
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

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseDTO> handleBadRequestException(BaseException ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setDetails(ex.getDetails());
        return ResponseEntity.status(ex.getStatus()).body(responseDTO);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDTO> handleNotFoundException(BaseException ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setDetails(ex.getDetails());
        return ResponseEntity.status(ex.getStatus()).body(responseDTO);
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<ResponseDTO> handleNoContentException(BaseException ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setDetails(ex.getDetails());
        return ResponseEntity.status(ex.getStatus()).body(responseDTO);
    }

}
