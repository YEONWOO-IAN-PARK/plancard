package com.junebay.plancard.card.service.impl;

import com.junebay.plancard.card.dto.CardDTO;
import com.junebay.plancard.card.mapper.CardMapper;
import com.junebay.plancard.card.service.CardService;
import com.junebay.plancard.common.dto.RequestDTO;
import com.junebay.plancard.common.dto.ResponseDTO;
import com.junebay.plancard.common.validator.RequestValidator;
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

    @Value("${response.ok.exist.detail}")
    private String exist;

    @Value("${response.ok.notExist.detail}")
    private String notExist;

    private final RequestValidator requestValidator;

    private final CardMapper cardMapper;

    @Override
    public ResponseDTO selectCards(RequestDTO requestDTO, String cardType) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<CardDTO> cardDTOList;

        requestValidator.validateRequest(requestDTO);   // RequestDTO Validation (Early return)

        if ("explore".equals(cardType)) {
            cardDTOList = cardMapper.selectExploreCards(requestDTO);
        } else {
            cardDTOList = cardMapper.selectMyCards(requestDTO);
        }

        setResponseDTO(requestDTO, responseDTO, cardDTOList);

        return responseDTO;
    }

    /**
     * Setting ResponseDTO After Validation
     */
    private void setResponseDTO(RequestDTO requestDTO, ResponseDTO responseDTO, List<CardDTO> cardDTOList) {

        if (!cardDTOList.isEmpty()) {
            responseDTO.setPagination(requestDTO.getPagination());
            responseDTO.setDetails(exist);
        } else {
            responseDTO.setDetails(notExist);
        }

        responseDTO.setResult(cardDTOList);
    }

}
