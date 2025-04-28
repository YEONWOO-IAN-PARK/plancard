package com.junebay.plancard.image.dto;

import lombok.Getter;

/**
 * @author : IAN
 * @date : 2025-04-25
 * @description : 사용자 요청 DTO에 매핑될 카드이미지 DTO
 */
@Getter
public class CardImageDTO {

    private long imageId;
    private String imageUrl;
    private String alt;
    private boolean isMain;

    /**
     "imageList": [
         {
             "imageId": 1,
             "imageUrl": "https://example.com/louvre.jpg",
             "alt": "루브르 이미지",
             "isMain": false
         }
     ]
     */
}
