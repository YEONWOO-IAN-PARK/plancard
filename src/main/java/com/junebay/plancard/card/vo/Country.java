package com.junebay.plancard.card.vo;

import java.time.LocalDateTime;

/**
 * @author : IAN
 * @date : 2024-11-05
 * @description :
 */
public class Country {

    private String id;
    private String name;
    private String description;
    private long mainImageId;   // Country Image Id
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
