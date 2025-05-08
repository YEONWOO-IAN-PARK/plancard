package com.junebay.plancard.card.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : IAN
 * @date : 2025-04-25
 * @description : 내 카드 > 태그 DTO
 */
@Getter
@Setter
public class MyCardTagDTO {

    private long tagId;
    private long myCardId;
    private String tagName;
}
