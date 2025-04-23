package com.junebay.plancard.common.validator;

import com.junebay.plancard.common.dto.RequestDTO;
import com.junebay.plancard.common.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : IAN
 * @date : 2025-04-22
 * @description : 사용자 요청에 대한 유효성 검사 클래스. (실질적인 예외처리는 Exception Handler 가 처리)
 */
@Component
@RequiredArgsConstructor
public class RequestValidator {

    @Value("${response.badRequest.detail}") private String badRequestDetails;

    /**
     * RequestDTO를 받아 사용자 요청의 유효성 검사 시행
     * 유효성 검사를 통과하지 못할 시, 각 상황에 맞는 예외를 반환한다.
     *
     * @param requestDTO  
     */
    public void validateRequest(RequestDTO requestDTO) {

        if (requestDTO.getPagination() == null || requestDTO.getSort() == null) {
            throw new BadRequestException(badRequestDetails);
        }

    }

}
