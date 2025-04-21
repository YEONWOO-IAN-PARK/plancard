package com.junebay.plancard.card.service.impl;

import com.junebay.plancard.card.dto.CardDTO;
import com.junebay.plancard.card.mapper.CardMapper;
import com.junebay.plancard.card.service.CardService;
import com.junebay.plancard.common.dto.RequestDTO;
import com.junebay.plancard.common.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Value("${response.detail.ok.exist.card}")
    private String exist;

    @Value("${response.detail.ok.notExist}")
    private String notExist;

    @Value("${response.detail.badRequest}")
    private String badRequest;

    private final CardMapper cardMapper;

    @Override
    public ResponseDTO exploreCards(RequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<CardDTO> exploreCards = new ArrayList<>();

        try {
            // 요청DTO 유효성 검사
            // TODO : 어떻게 처리할지 고민...
            validCheckForRequestDTO(requestDTO, responseDTO);

            exploreCards = cardMapper.selectExploreCards(requestDTO);

            if (exploreCards.size() > 0) {
                responseDTO.setStatus(200);
                responseDTO.setMessage("OK");
                responseDTO.setData(exploreCards);
                responseDTO.setPagination(requestDTO.getPagination());
                responseDTO.setDetails(exist);
            } else {
                responseDTO.setStatus(200);
                responseDTO.setMessage("OK");
                responseDTO.setData(exploreCards);
                responseDTO.setDetails(notExist);
            }

        } catch (Exception e) {
            log.warn("Exception Occurred while exploreCards() : {}", e.getMessage());
        }

        return responseDTO;
    }

    private void validCheckForRequestDTO(RequestDTO requestDTO, ResponseDTO responseDTO) {
        if (requestDTO.getPagination() == null || requestDTO.getSort() == null) {
            setBadRequest(responseDTO);
        }
    }

    private void setBadRequest(ResponseDTO responseDTO) {
        responseDTO.setStatus(400);
        responseDTO.setMessage("Bad Request");
        responseDTO.setDetails(badRequest);
    }

}
