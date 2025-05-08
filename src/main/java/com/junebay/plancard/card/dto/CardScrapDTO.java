package com.junebay.plancard.card.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : IAN
 * @date : 2025-05-08
 * @description : 카드 스크랩이 성공적으로 이루어졌을 경우 (201)에 대한 응답 DTO
 */
@Getter
@Setter
public class CardScrapDTO {

    private long cardId;
    private boolean scrap;
}
