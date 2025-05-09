package com.junebay.plancard.card.mapper;

import com.junebay.plancard.card.dto.CardDTO;
import com.junebay.plancard.card.dto.MyCardTagDTO;
import com.junebay.plancard.common.dto.RequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     */
    List<CardDTO> selectExploreCards(@Param("requestDTO") RequestDTO requestDTO, @Param("userId") long userId);

    /**
     * 내 카드 목록을 조회하는 기능
     */
    List<CardDTO> selectMyCards(@Param("requestDTO") RequestDTO requestDTO, @Param("userId") long userId);

    /**
     * 탐험카드를 조회하는 기능
     */
    CardDTO selectExploreCardOne(@Param("cardType") String cardType, @Param("cardId") long cardId, @Param("userId") long userId);

    /**
     * 내카드를 조회하는 기능
     */
    CardDTO selectMyCardOne(@Param("cardType") String cardType, @Param("cardId") long cardId, @Param("userId") long userId);

    /**
     * 탐험 카드를 스크랩하는 기능
     */
    void insertCardScrap(@Param("cardId") long cardId, @Param("userId") long userId);

    /**
     * 카드 ID와 유저 ID를 전달받아, 카드의 스크랩정보를 조회하는 기능
     * 결과객체의 ID가 존재하면 카드는 있으나 스크랩이 안된상태. ID가 존재하지않을 경우 카드 자체가 없음
     */
    CardDTO selectScrappedCardDTO(@Param("cardId") long cardId, @Param("userId") long userId);

    /**
     * 탐험 카드를 스크랩 테이블에서 삭제하는 기능
     */
    void deleteCardScrap(@Param("cardId") long cardId, @Param("userId") long userId);

    /**
     * 카드 유형, 카드 ID, 메모내용을 전달받아 (내 카드 커스텀) 메모를 업데이트하는 기능
     */
    void updateMemo(@Param("cardId") long cardId, @Param("memo")String memo);

    /**
     * 카드 ID, 태그명을 전달받아 (내 카드 커스텀) 태그를 추가하는 기능
     */
    void insertMyCardTag(MyCardTagDTO myCardTagDTO);

    /**
     * 태그 ID를 전달받아 (내 카드 커스텀) 태그를 삭제하는 기능
     */
    void deleteMyCardTag(@Param("tagId") long tagId);
}
