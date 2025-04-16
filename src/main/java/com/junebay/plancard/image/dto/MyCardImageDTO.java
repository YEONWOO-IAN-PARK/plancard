package com.junebay.plancard.image.dto;

/**
 * @author : IAN
 * @date : 2025-04-16
 * @description : 프론트 <-> 서버간 api 설계 시, 응답 구조체의 프로퍼티명에 차이가 있어서 중간 완충역할로 DTO 추가 생성
 */
public class MyCardImageDTO {

    private long myCardImageId;
    private String imageUrl;
    private String alt;
    private boolean isMain;

    /**
     "myImageList": [
         {
             "myCardImageId": 1,
             "imageUrl": "https://example.com/louvre.jpg",
             "alt": "루브르 이미지",
             "isMain": false
         }
     ]
     */
}
