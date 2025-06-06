package com.junebay.plancard.card.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author : IAN
 * @date : 2024-11-07
 * @description : 내 카드 > 태그 정보
 */
@Getter
@Setter
public class MyCardTag {

    private long id;
    private long myCardId;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
