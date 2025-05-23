package com.junebay.plancard.image.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : IAN
 * @date : 2025-04-25
 * @description : 사용자 요청 DTO에 매핑될 카드이미지 DTO
 */
@Getter
@Setter
public class CardImageDTO {

    private long imageId;
    private String filename;
    private String filepath;
    private String imageUrl;
    private String alt;

}
