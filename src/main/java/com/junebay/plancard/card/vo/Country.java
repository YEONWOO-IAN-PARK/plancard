package com.junebay.plancard.card.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author : IAN
 * @date : 2024-11-05
 * @description :
 */
@Getter
@Setter
public class Country {

    private String id;
    private String name;
    private String description;
    private long mainImageId;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
