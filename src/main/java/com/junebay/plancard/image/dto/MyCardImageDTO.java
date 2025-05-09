package com.junebay.plancard.image.dto;

import lombok.Getter;

/**
 * @author : IAN
 * @date : 2025-04-16
 * @description : 사용자 요청 DTO에 매핑될 내카드이미지 DTO
 */
@Getter
public class MyCardImageDTO {

    private long myImageId;
    private long myCardId;
    private String filename;
    private String filepath;
    private String imageUrl;
    private String alt;
}
