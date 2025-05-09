package com.junebay.plancard.card.dto;

import com.junebay.plancard.image.dto.MyCardImageDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : IAN
 * @date : 2025-04-16
 * @description : 내 카드 DTO
 */
@Getter
@Setter
public class MyCardDTO extends CardDTO {

    private long myCardId;
    private String myMemo;
    private String mainImageType;   // ### 수정 후 버전 ###
    private List<MyCardImageDTO> myImageList;
    private List<MyCardTagDTO> myTagList;
}
