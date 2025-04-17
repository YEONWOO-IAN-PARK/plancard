package com.junebay.plancard.card.controller;

import com.junebay.plancard.card.service.CardService;
import com.junebay.plancard.common.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

    private final CardService cardService;

    /**
     * 탐험 카드 목록을 보여주는 요청핸들러메서드
     * @return
     */
    @PostMapping("/search/explore")
    public ResponseEntity<ResponseDTO> exploreCards() {


    }

}
