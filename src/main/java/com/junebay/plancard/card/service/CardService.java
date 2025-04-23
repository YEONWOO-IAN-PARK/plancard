package com.junebay.plancard.card.service;

import com.junebay.plancard.common.dto.RequestDTO;
import com.junebay.plancard.common.dto.ResponseDTO;

/**
 * @author : IAN
 * @date : 2025-04-17
 * @description :
 */
public interface CardService {

    /**
     * 탐험카드 목록을 가져오는 기능
     * @param requestDTO 요청 DTO
     * @param cardType 조회카드 유형("explore" || "my")
     */
    ResponseDTO selectCards(RequestDTO requestDTO, String cardType);

}
