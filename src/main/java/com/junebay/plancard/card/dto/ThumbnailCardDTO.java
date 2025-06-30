package com.junebay.plancard.card.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 내 플랜 목록 조회시 보여줄 DTO 클래스
 */
@Getter
@Setter
public class ThumbnailCardDTO extends BasicCardDTO {

//    아래 두 항목들 안필요한지 찬이에게 물어보고 추가/삭제 해야함
//    내 플랜 목록 조회 시 사용하기 때문에 필요하지 않을거라는 내 생각
    private long imageId;
    private String alt;
    private String imageUrl;
}
