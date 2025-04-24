package com.junebay.plancard.common.validator;

import com.junebay.plancard.card.dto.CardDTO;
import com.junebay.plancard.common.dto.RequestDTO;
import com.junebay.plancard.common.enums.ErrorCode;
import com.junebay.plancard.common.exception.BadRequestException;
import com.junebay.plancard.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author : IAN
 * @date : 2025-04-22
 * @description : 유효성 검사 클래스. (실질적인 예외처리는 GlobalExceptionHandler 가 처리)
 */
@Component
@RequiredArgsConstructor
public class CustomValidator {

    /**
     * RequestDTO를 받아 사용자 요청의 유효성 검사 시행
     * 유효성 검사를 통과하지 못할 시, 각 상황에 맞는 예외를 반환한다.
     */
    public void validateRequest(RequestDTO requestDTO) {

        if (requestDTO.getPagination() == null || requestDTO.getSort() == null) {
            throw new BadRequestException(ErrorCode.BAD_REQUEST);
        }
    }

    /**
     * RequestDTO를 받아 사용자 요청의 유효성 검사 시행
     * 유효성 검사를 통과하지 못할 시, 각 상황에 맞는 예외를 반환한다.
     */
    public void validateCardOne(CardDTO cardDTO) {

        if (cardDTO == null || cardDTO.getCardId() == 0) {
            throw new NotFoundException(ErrorCode.NOT_FOUND);
        }
    }

}
