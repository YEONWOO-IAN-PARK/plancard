package com.junebay.plancard.image.vo;

import com.junebay.plancard.image.vo.Image;

import java.time.LocalDateTime;

/**
 * @author : IAN
 * @date : 2024-11-05
 * @description :
 */
public class CardImage extends Image{

    private long id;
    private long cardId;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
