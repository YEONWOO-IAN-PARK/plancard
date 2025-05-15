package com.junebay.plancard.card.controller;

import com.junebay.plancard.card.dto.MyCardMemoDTO;
import com.junebay.plancard.card.dto.MyCardTagDTO;
import com.junebay.plancard.card.service.CardService;
import com.junebay.plancard.common.dto.RequestDTO;
import com.junebay.plancard.common.dto.ResponseDTO;
import com.junebay.plancard.image.dto.MainImageRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 탐험카드 스크랩 해제 요청핸들러메서드
     */
    @DeleteMapping("/{cardId}/scrap")
    public ResponseEntity<ResponseDTO> deleteScrapCard(@PathVariable long cardId) {
        cardService.unscrapCard(cardId);
        return ResponseEntity.status(204).build();
    }

    /**
     * 내 카드 커스텀 메모 등록
     */
    @PatchMapping("/{cardType}/{myCardId}/memo")
    public ResponseEntity<ResponseDTO> updateMemo(@PathVariable String cardType, @PathVariable long myCardId, @RequestBody MyCardMemoDTO memoDTO) {
        cardService.updateMemo(cardType, myCardId, memoDTO.getMemo());
        return ResponseEntity.status(204).build();
    }

    /**
     * 내 카드 커스텀 태그 등록
     */
    @PostMapping("/{cardType}/{myCardId}/tag")
    public ResponseEntity<ResponseDTO> tag(@PathVariable String cardType, @PathVariable long myCardId, @RequestBody MyCardTagDTO tagDTO) {
        ResponseDTO responseDTO = cardService.insertMyCardTag(cardType, myCardId, tagDTO.getTagName());
        return ResponseEntity.status(201).body(responseDTO);
    }

    /**
     * 내 카드 커스텀 태그 삭제
     */
    @DeleteMapping("/{cardType}/{myCardId}/tag")
    public ResponseEntity<ResponseDTO> deleteTag(@PathVariable String cardType, @PathVariable long myCardId, @RequestBody MyCardTagDTO tagDTO) {
        cardService.deleteMyCardTag(cardType, myCardId, tagDTO.getTagId());
        return ResponseEntity.status(204).build();
    }

    /**
     * 내 카드 이미지 등록
     */
    @PostMapping("/{cardType}/{myCardId}/images")
    public ResponseEntity<ResponseDTO> insertMyCardImage(@PathVariable String cardType, @PathVariable long myCardId, @RequestParam("image") MultipartFile imageFile)  {
        ResponseDTO responseDTO = cardService.insertMyCardImage(cardType, myCardId, imageFile);
        return ResponseEntity.status(201).body(responseDTO);
    }

    /**
     * 내 카드 이미지 삭제
     */
    @DeleteMapping("/{cardType}/{myCardId}/images/{myCardImageId}")
    public ResponseEntity<ResponseDTO> deleteMyCardImage(@PathVariable String cardType, @PathVariable long myCardId, @PathVariable long myCardImageId) {
        cardService.deleteMyCardImage(cardType, myCardId, myCardImageId);
        return ResponseEntity.status(204).build();
    }


    /**
     * 내 카드 이미지 메인 이미지 등록
     */
    @PatchMapping("/{cardType}/{myCardId}/images/{myCardImageId}/main")
    public ResponseEntity<ResponseDTO> mainImage(@PathVariable String cardType, @PathVariable long myCardId, @PathVariable long myCardImageId, @RequestBody MainImageRequestDTO mainImageRequestDTO) {
        cardService.updateMyCardMainImage(cardType, myCardId, myCardImageId, mainImageRequestDTO);
        return ResponseEntity.status(204).build();
    }
}
