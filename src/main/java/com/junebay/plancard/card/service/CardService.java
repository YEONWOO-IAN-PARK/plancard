package com.junebay.plancard.card.service;

import com.junebay.plancard.common.dto.RequestDTO;
import com.junebay.plancard.common.dto.ResponseDTO;

/**
 * @author : IAN
 * @date : 2025-04-17
 * @description : 카드 관련 작업을 수행하는 서비스 인터페이스
 */
public interface CardService {

    /**
     * 탐험카드 또는 내 카드 목록을 가져오는 기능
     * @param requestDTO 요청 DTO
     * @param cardType 조회카드 유형("explore" || "my")
     */
    ResponseDTO selectCards(RequestDTO requestDTO, String cardType);

    /**
     * 탐험카드 또는 내 카드를 가져오는 기능
     * @param cardType 조회카드 유형("explore" || "my")
     * @param cardId   조회카드 ID
     */
    ResponseDTO selectOneCard(String cardType, long cardId);

    /**
     * 탐험카드 스크랩하는 기능
     * @param cardId 탐험카드 ID
     */
    ResponseDTO scrapCard(long cardId);

    /**
     * 탐험카드 스크랩 해제하는 기능
     * @param cardId 탐험카드 ID
     */
    void unscrapCard(long cardId);
}
