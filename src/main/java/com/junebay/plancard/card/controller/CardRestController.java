package com.junebay.plancard.card.controller;

import com.junebay.plancard.card.service.CardService;
import com.junebay.plancard.common.dto.RequestDTO;
import com.junebay.plancard.common.dto.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : IAN
 * @date : 2025-03-25
 * @description : 카드에 관련된 작업을 하는 Rest Controller
 */
@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardRestController {

    private static final Logger log = LoggerFactory.getLogger(CardRestController.class);

    private final CardService cardService;

    /**
     * 탐험 카드 목록을 보여주는 요청핸들러메서드
     * @return ResponseDTO
     */
    @PostMapping("/search/explore")
    public ResponseEntity<ResponseDTO> exploreCards(@RequestBody RequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            responseDTO = cardService.exploreCards(requestDTO);
            if (responseDTO.getStatus() == 400) return ResponseEntity.badRequest().body(responseDTO);
        } catch (Exception e) {
            log.warn("Exception Occurred while exploreCards() : {}", e.getMessage());
        }

        return ResponseEntity.ok(responseDTO);
    }

}
