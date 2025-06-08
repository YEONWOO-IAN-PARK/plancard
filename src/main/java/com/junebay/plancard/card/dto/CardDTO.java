package com.junebay.plancard.card.dto;

import com.junebay.plancard.image.dto.CardImageDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : IAN
 * @date : 2025-03-25
 * @description : 카드 DTO
 */
@Getter
@Setter
public class CardDTO extends BasicCardDTO {

    private List<String> theme;
    private long mainImageId;       // ### 수정 후 버전 ###
    private String mapLink;
    private List<CardImageDTO> imageList;

    /**
     *  "cardId": "1",
     *  "title": "Eiffel Tower",
     *  "description": "A wrought iron lattice tower on the Champ de Mars in Paris, France.",
     *  "country": "France",
     *  "city": "Paris",
     *  "theme": ["night","elastic"],
     *  "category": "attraction",
     *  "rating": 4,
     *  "mainImageId": 1,       // ### 수정 후 버전 ###
        "scrap": true,
     *  "mapLink": "https://googlemap.com/france/paris/972234",
     *  "imageList": [
     *     {
     *        "imageId": 1,
     *        "imageUrl": "https://example.com/eiffel.jpg",
     *        "alt": "루브르 이미지"
     *        // "isMain": true   수정 전 버전
     *     }
     *  ]
     */
}
