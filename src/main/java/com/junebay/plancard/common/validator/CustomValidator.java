package com.junebay.plancard.common.validator;

import com.junebay.plancard.card.dto.CardDTO;
import com.junebay.plancard.common.dto.RequestDTO;
import com.junebay.plancard.common.enums.StatusCode;
import com.junebay.plancard.common.exception.BadRequestException;
import com.junebay.plancard.common.exception.NoContentException;
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
        if (requestDTO.getPagination() == null || requestDTO.getSort() == null) throw new BadRequestException(StatusCode.BAD_REQUEST);
    }

    /**
     * RequestDTO를 받아 사용자 요청의 유효성 검사 시행
     * 유효성 검사를 통과하지 못할 시, 각 상황에 맞는 예외를 반환한다.
     */
    public void validateCardOne(CardDTO cardDTO) {
        if (cardDTO == null || cardDTO.getCardId() == 0) throw new NotFoundException(StatusCode.NOT_FOUND);
    }

    /**
     * CardDTO를 받아 CardDTO의 스크랩유무를 위한 유효성 검사 시행
     * 유효성 검사를 통과하지 못할 시, 각 상황에 맞는 예외를 반환한다.
     * @param scrapCriteria 스크랩 요청 기준   true : 스크랩 || false : 스크랩 해제
     */
    public void validateCardScrap(CardDTO cardDTO, boolean scrapCriteria) {
        // 존재하지 않는 카드번호
        if (cardDTO == null) throw new NotFoundException(StatusCode.NOT_FOUND);

        if (scrapCriteria) {
            // 카드 스크랩 요청 시, 프로세스 흐름상 요청 할 수 없는 구조라 예외로 지정한다.
            if (cardDTO.isScrap()) throw new NoContentException(StatusCode.NO_CONTENT);
        }
    }
}
