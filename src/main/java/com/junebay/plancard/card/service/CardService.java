package com.junebay.plancard.card.service;

import com.junebay.plancard.card.dto.CardDTO;

import java.util.List;

/**
 * @author : IAN
 * @date : 2025-04-17
 * @description :
 */
public interface CardService {

    /**
     * 탐험카드 목록을 가져오는 기능
     * @return
     */
    List<CardDTO> exploreCards();
}
