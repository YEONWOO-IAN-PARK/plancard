package com.junebay.plancard.card.service.impl;

import com.junebay.plancard.card.dto.CardDTO;
import com.junebay.plancard.card.dto.CardScrapDTO;
import com.junebay.plancard.card.dto.MyCardTagDTO;
import com.junebay.plancard.card.mapper.CardMapper;
import com.junebay.plancard.card.service.CardService;
import com.junebay.plancard.common.dto.RequestDTO;
import com.junebay.plancard.common.dto.ResponseDTO;
import com.junebay.plancard.common.validator.CustomValidator;
import com.junebay.plancard.image.dto.MyCardImageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author : IAN
 * @date : 2025-04-17
 * @description : 카드 관련 비즈니스 로직을 구현하는 서비스 구현체 클래스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    @Value("${response.ok.exist.one.detail}") private String existCard;
    @Value("${response.ok.exist.list.detail}") private String existCardList;
    @Value("${response.ok.notExist.list.detail}") private String notExistCardList;
    @Value("${response.ok.scrapped.detail}") private String scrapped;
    @Value("${response.ok.tagged.detail}") private String insertedTag;
    private final CustomValidator customValidator;
    private final CardMapper cardMapper;

    @Override
    public ResponseDTO selectCards(RequestDTO requestDTO, String cardType) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<CardDTO> cardDTOList;

        customValidator.validateRequest(requestDTO);   // RequestDTO Validation (throw 400)

        long userId = 2;    // TODO : 임시 유저 아이디. 스프링 시큐리티 적용 시 대체한다.

        if ("explore".equals(cardType)) {
            cardDTOList = cardMapper.selectExploreCards(requestDTO, userId);
        } else {
            cardDTOList = cardMapper.selectMyCards(requestDTO, userId);
        }

        setResponseDTO(requestDTO, responseDTO, cardDTOList);

        return responseDTO;
    }

    @Override
    public ResponseDTO selectOneCard(String cardType, long cardId) {
        ResponseDTO responseDTO = new ResponseDTO();
        CardDTO cardDTO;

        long userId = 2;    // TODO : 임시 유저 아이디. 스프링 시큐리티 적용 시 대체한다.

        if ("explore".equals(cardType)) {
            cardDTO = cardMapper.selectExploreCardOne(cardType, cardId, userId);
        } else {
            cardDTO = cardMapper.selectMyCardOne(cardType, cardId, userId);
        }

        customValidator.validateCardOne(cardDTO);   // CardDTO Validation(404)

        setResponseDTO(responseDTO, cardDTO, existCard);

        return responseDTO;
    }

    @Override
    public ResponseDTO scrapCard(long cardId) {
        ResponseDTO responseDTO = new ResponseDTO();
        CardScrapDTO scrapDTO = new CardScrapDTO();

        long userId = 2;    // TODO : 임시 유저 아이디. 스프링 시큐리티 적용 시 대체한다.

        CardDTO cardDTO = cardMapper.selectScrappedCardDTO(cardId, userId);
        customValidator.validateCardOne(cardDTO);    // card Validation(404)
        customValidator.validateCardScrap(cardDTO, true);   // card scrap Validation(204)

        if (!cardDTO.isScrap()) {
            cardMapper.insertCardScrap(cardId, userId);

            scrapDTO.setCardId(cardDTO.getCardId());
            scrapDTO.setScrap(cardDTO.isScrap());
            setResponseDTO(responseDTO, scrapDTO, scrapped);
        }

        return responseDTO;
    }

    @Override
    public void unscrapCard(long cardId) {

        long userId = 2;    // TODO : 임시 유저 아이디. 스프링 시큐리티 적용 시 대체한다.

        CardDTO cardDTO = cardMapper.selectScrappedCardDTO(cardId, userId);
        customValidator.validateCardOne(cardDTO);   // card Validation(404)
        customValidator.validateCardScrap(cardDTO, false);  // card scrap Validation(204)

        if (cardDTO.isScrap()) {
            cardMapper.deleteCardScrap(cardId, userId);
        }
    }

    @Override
    public void updateMemo(String cardType, long cardId, String memo) {

        long userId = 2;    // TODO : 임시 유저 아이디. 스프링 시큐리티 적용 시 대체한다.

        CardDTO cardDTO = cardMapper.selectMyCardOne(cardType, cardId, userId);
        customValidator.validateCardOne(cardDTO);   // card Validation (404)

        cardMapper.updateMemo(cardId, memo);

    }

    @Override
    public ResponseDTO insertMyCardTag(String cardType, long myCardId, String tagName) {
        ResponseDTO responseDTO = new ResponseDTO();
        MyCardTagDTO myCardTagDTO = new MyCardTagDTO();

        long userId = 2;    // TODO : 임시 유저 아이디. 스프링 시큐리티 적용 시 대체한다.

        CardDTO cardDTO = cardMapper.selectMyCardOne(cardType, myCardId, userId);
        customValidator.validateCardOne(cardDTO);   // card Validation(404)

        myCardTagDTO.setMyCardId(myCardId);
        myCardTagDTO.setTagName(tagName);
        cardMapper.insertMyCardTag(myCardTagDTO);

        setResponseDTO(responseDTO, myCardTagDTO, insertedTag);

        return responseDTO;
    }

    @Override
    public void deleteMyCardTag(String cardType, long myCardId, long tagId) {
        long userId = 2;    // TODO : 임시 유저 아이디. 스프링 시큐리티 적용 시 대체한다.

        CardDTO cardDTO = cardMapper.selectMyCardOne(cardType, myCardId, userId);
        customValidator.validateCardOne(cardDTO);           // card Validation(404)
        customValidator.validateCardTag(cardDTO, tagId);    // card tag Validation(404)

        cardMapper.deleteMyCardTag(tagId);
    }

    @Override
    public ResponseDTO insertMyCardImage(String cardType, long myCardId, MultipartFile imageFile) {
        ResponseDTO responseDTO = new ResponseDTO();
        MyCardImageDTO myCardImageDTO = new MyCardImageDTO();

        long userId = 2;

        // 1. myCardId로 카드가 존재하는지 먼저 확인
        CardDTO cardDTO = cardMapper.selectMyCardOne(cardType, myCardId, userId);
        customValidator.validateCardOne(cardDTO);   // card Validation(404)

        // 2. 첨부한 파일의 유효성 검사 실시 (용량, 확장자)
        customValidator.validateCardImage(imageFile);

        // 3. 서버에 저장

        // 4. DB에 저장

        // 5. responseDTO 세팅 및 반환

        return responseDTO;
    }

    /**
     * 내 카드 태그 추가 성공 시, 반환을 위한 ResponseDTO 세팅
     */
    private void setResponseDTO(ResponseDTO responseDTO, MyCardTagDTO myCardTagDTO, String insertedTag) {
        responseDTO.setResult(myCardTagDTO);
        responseDTO.setDetails(insertedTag);
    }

    /**
     * 카드 스크랩 성공 시, 반환을 위한 ResponseDTO 세팅
     */
    private void setResponseDTO(ResponseDTO responseDTO, CardScrapDTO scrapDTO, String detailMessage) {
        responseDTO.setDetails(detailMessage);
        responseDTO.setResult(scrapDTO);
    }

    /**
     * 단일 카드 반환을 위한 ResponseDTO 세팅
     */
    private void setResponseDTO(ResponseDTO responseDTO, CardDTO cardDTO, String details) {

        if (cardDTO != null) {
            responseDTO.setDetails(details);
        }
        responseDTO.setResult(cardDTO);
    }

    /**
     * 카드목록 반환을 위한 ResponseDTO 세팅
     */
    private void setResponseDTO(RequestDTO requestDTO, ResponseDTO responseDTO, List<CardDTO> cardDTOList) {

        if (!cardDTOList.isEmpty()) {
            responseDTO.setPagination(requestDTO.getPagination());
            responseDTO.setDetails(existCardList);
        } else {
            responseDTO.setDetails(notExistCardList);
        }

        responseDTO.setResult(cardDTOList);
    }
}
