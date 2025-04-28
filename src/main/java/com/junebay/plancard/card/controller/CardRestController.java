package com.junebay.plancard.card.controller;

import com.junebay.plancard.card.service.CardService;
import com.junebay.plancard.common.dto.RequestDTO;
import com.junebay.plancard.common.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

/**
 * @author : IAN
 * @date : 2025-03-25
 * @description : 카드에 관련된 작업을 하는 Rest Controller
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/cards")
public class CardRestController {

    private final CardService cardService;

    /**
     * 탐험카드 또는 내 카드 목록 조회 요청핸들러메서드
     */
    @PostMapping("/search/{cardType}")
    public ResponseEntity<ResponseDTO> cards(@RequestBody RequestDTO requestDTO, @PathVariable String cardType) {
        ResponseDTO responseDTO = cardService.selectCards(requestDTO, cardType);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * 탐험카드 또는 내 카드 상세 조회 요청핸들러메서드
     */
    @GetMapping("/{cardType}/{cardId}")
    public ResponseEntity<ResponseDTO> card(@PathVariable String cardType, @PathVariable long cardId) {
        ResponseDTO responseDTO = cardService.selectOneCard(cardType, cardId);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * 탐험카드 스크랩 요청핸들러메서드
     */
    @PutMapping("/{cardId}/scrap")
    public ResponseEntity<ResponseDTO> scrapCard(@PathVariable long cardId) {
        ResponseDTO responseDTO = cardService.scrapCard(cardId);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @DeleteMapping("/{cardId}/scrap")
    public ResponseEntity<ResponseDTO> unscrapCard(@PathVariable long cardId) {
        cardService.unscrapCard(cardId);
        return ResponseEntity.status(204).build();
    }

}
