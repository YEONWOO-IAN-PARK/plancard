package com.junebay.plancard.plan.vo;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author : IAN
 * @date : 2024-11-07
 * @description :
 */
@Builder
public class TempStorageCard {

    private long id;
    private long tempStorageId;
    private long cardId;
    private long myCardId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
