package com.junebay.plancard.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author : IAN
 * @date : 2024-09-09
 * @description :
 */
@Getter
@Setter
public class BaseDataEntity {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
