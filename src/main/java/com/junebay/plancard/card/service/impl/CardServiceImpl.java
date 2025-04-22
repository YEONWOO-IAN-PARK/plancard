package com.junebay.plancard.card.service.impl;

import com.junebay.plancard.card.dto.CardDTO;
import com.junebay.plancard.card.mapper.CardMapper;
import com.junebay.plancard.card.service.CardService;
import com.junebay.plancard.common.dto.RequestDTO;
import com.junebay.plancard.common.dto.ResponseDTO;
import com.junebay.plancard.common.exception.InternalServerErrorException;
import com.junebay.plancard.common.validator.RequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : IAN
 * @date : 2025-04-17
 * @description :
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

    @Value("${response.internalError.message}") private String internalErrorMessage;
    @Value("${response.internalError.status}") private int internalErrorStatus;
    @Value("${response.internalError.detail}") private String internalErrorDetails;

    @Override
    public ResponseDTO exploreCards(RequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        // RequestDTO Validation (Early return)
        requestValidator.validateRequest(requestDTO);

        List<CardDTO> exploreCards = cardMapper.selectExploreCards(requestDTO);

        // Setting ResponseDTO After Validation
        responseDTO.setStatus(200);
        responseDTO.setMessage("OK");
        responseDTO.setData(exploreCards);
        if (!exploreCards.isEmpty()) {
            responseDTO.setPagination(requestDTO.getPagination());
            responseDTO.setDetails(exist);
        } else {
            responseDTO.setDetails(notExist);
        }

        return responseDTO;
    }

}
