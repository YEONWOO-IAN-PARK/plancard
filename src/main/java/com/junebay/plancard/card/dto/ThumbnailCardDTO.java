package com.junebay.plancard.card.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 내 플랜 목록 조회시 보여줄 DTO 클래스
 */
@Getter
@Setter
public class ThumbnailCardDTO extends BasicCardDTO {

//    private long imageId;
//    private String alt;
    private String imageUrl;
}
