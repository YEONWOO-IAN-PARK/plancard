package com.junebay.plancard.card.dto;

import com.junebay.plancard.common.vo.Tag;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : IAN
 * @date : 2025-04-25
 * @description : 내 카드 > 태그 DTO
 */
@Getter
@Setter
public class MyCardTagDTO extends Tag {
    private long myCardId;
}
