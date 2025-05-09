package com.junebay.plancard.card.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : IAN
 * @date : 2024-11-05
 * @description :
 */
@Getter
@Setter
public class MyCard {

    private long id;
    private long cardId;
    private long userId;
    private String memo;
    private String mainImageType;
    private long mainImageId;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
