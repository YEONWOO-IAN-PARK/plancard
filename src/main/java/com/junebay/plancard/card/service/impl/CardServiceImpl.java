package com.junebay.plancard.card.service.impl;

import com.junebay.plancard.card.dto.CardDTO;
import com.junebay.plancard.card.mapper.CardMapper;
import com.junebay.plancard.card.service.CardService;
import com.junebay.plancard.common.dto.RequestDTO;
import com.junebay.plancard.common.dto.ResponseDTO;
import com.junebay.plancard.common.validator.CustomValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    private final CustomValidator customValidator;
    private final CardMapper cardMapper;

    @Override
    public ResponseDTO selectCards(RequestDTO requestDTO, String cardType) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<CardDTO> cardDTOList;

        customValidator.validateRequest(requestDTO);   // RequestDTO Validation (throw 400)

        if ("explore".equals(cardType)) {
            cardDTOList = cardMapper.selectExploreCards(requestDTO);
        } else {
            cardDTOList = cardMapper.selectMyCards(requestDTO);
        }

        setResponseDTO(requestDTO, responseDTO, cardDTOList);

        return responseDTO;
    }

    @Override
    public ResponseDTO selectOneCard(String cardType, long cardId) {
        ResponseDTO responseDTO = new ResponseDTO();
        CardDTO cardDTO;

        if ("explore".equals(cardType)) {
            cardDTO = cardMapper.selectExploreCardOne(cardId);
        } else {
            cardDTO = cardMapper.selectMyCardOne(cardId);
        }

        customValidator.validateCardOne(cardDTO);   // CardDTO Validation(throw 404)

        setResponseDTO(responseDTO, cardDTO);

        return responseDTO;
    }

    /**
     * 단일 카드 반환을 위한 ResponseDTO 세팅
     */
    private void setResponseDTO(ResponseDTO responseDTO, CardDTO cardDTO) {

        if (cardDTO != null) {
            responseDTO.setDetails(existCard);
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
