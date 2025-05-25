package com.junebay.plancard.card.service;

import com.junebay.plancard.common.dto.SearchDTO;
import com.junebay.plancard.common.dto.ResponseDTO;
import com.junebay.plancard.image.dto.CardMainImageDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : IAN
 * @date : 2025-04-17
 * @description : 카드 관련 작업을 수행하는 서비스 인터페이스
 */
public interface CardService {

    /**
     * 탐험카드 또는 내 카드 목록을 가져오는 기능
     * @param searchDTO 요청 DTO
     * @param cardType 조회카드 유형("explore" || "my")
     */
    ResponseDTO selectCards(SearchDTO searchDTO, String cardType);

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

    /**
     * 내 카드의 커스텀 메모를 등록&수정하는 기능
     * @param cardType 조회카드 유형("explore" || "my")
     * @param myCardId 내 카드 ID
     * @param  memo 등록/수정/삭제 할 내 카드 메모
     */
    void updateMemo(String cardType, long myCardId, String memo);

    /**
     * 내 카드의 커스텀 태그를 등록하는 기능
     * @param cardType 조회카드 유형("explore" || "my")
     * @param myCardId 내 카드 ID
     * @param tagName 등록할 태그명
     */
    ResponseDTO insertMyCardTag(String cardType, long myCardId, String tagName);

    /**
     * 내 카드의 커스텀 태그를 삭제하는 기능
     * @param cardType 조회카드 유형("explore" || "my")
     * @param myCardId 내 카드 ID
     * @param tagId 삭제할 태그 ID
     */
    void deleteMyCardTag(String cardType, long myCardId, long tagId);

    /**
     * 내 카드의 커스텀 이미지를 추가하는 기능
     * @param cardType 조회카드 유형("explore" || "my")
     * @param myCardId 내 카드 ID
     * @param imageFile 첨부한 이미지 파일
     */
    ResponseDTO insertMyCardImage(String cardType, long myCardId, MultipartFile imageFile);

    /**
     * 내 카드의 커스텀 이미지를 삭제하는 기능
     * @param cardType 조회카드 유형("explore" || "my")
     * @param myCardId 내 카드 ID
     * @param myCardImageId 내 카드 이미지 ID
     */
    void deleteMyCardImage(String cardType, long myCardId, long myCardImageId);

    /**
     * 내 카드 대표 이미지를 등록하는 기능
     * @param cardType 조회카드 유형("explore" || "my")
     * @param myCardId 내 카드 ID
     * @param myCardImageId 내 이미지 카드 ID
     * @param mainImageSearchDTO 메인이미지 타입 정보 ("C" || "M")
     */
    void updateMyCardMainImage(String cardType, long myCardId, long myCardImageId, CardMainImageDTO mainImageSearchDTO);
}
