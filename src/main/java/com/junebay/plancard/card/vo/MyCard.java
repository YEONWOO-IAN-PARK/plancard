package com.junebay.plancard.card.vo;

import java.time.LocalDateTime;

/**
 * @author : IAN
 * @date : 2024-11-05
 * @description :
 */
public class MyCard {

    private long id;
    private long cardId;
    private long userId;
    private String memo;
    private char imageType;
    private long mainImageId;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
