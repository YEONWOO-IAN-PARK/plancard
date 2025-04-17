package com.junebay.plancard.card.mapper;

import com.junebay.plancard.card.dto.CardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : IAN
 * @date : 2025-04-17
 * @description : Card 관련 작업을 하는 MyBatis Mapper 인터페이스
 */
@Mapper
public interface CardMapper {

    /**
     * 탐험 카드 목록을 조회하는 기능
     * @return 탐험카드 목록
     */
    List<CardDTO> selectExploreCards();
}
