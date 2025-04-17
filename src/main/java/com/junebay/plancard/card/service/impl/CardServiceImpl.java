package com.junebay.plancard.card.service.impl;

import com.junebay.plancard.card.dto.CardDTO;
import com.junebay.plancard.card.mapper.CardMapper;
import com.junebay.plancard.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : IAN
 * @date : 2025-04-17
 * @description :
 */
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private static final Logger log = LoggerFactory.getLogger(CardServiceImpl.class);

    private final CardMapper cardMapper;

    @Override
    public List<CardDTO> exploreCards() {


        try {
            List<CardDTO> exploreCards = cardMapper.selectExploreCards();


        } catch (Exception e) {
            log.warn("");
        }

        return null;
    }


}
