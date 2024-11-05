package com.junebay.plancard.plan.vo;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author : IAN
 * @date : 2024-11-07
 * @description :
 */
@Builder
public class TempStorage {

    private long id;
    private long userId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
