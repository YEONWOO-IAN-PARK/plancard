package com.junebay.plancard.image.vo;

import java.time.LocalDateTime;

/**
 * @author : IAN
 * @date : 2024-11-05
 * @description :
 */
public class MyCardImage extends Image {

    private long id;
    private long myCardId;
    private boolean isActive;
    private char imageType; // 'C' or 'M'. C일 경우 imageId는 CardImageId가 되고 M일 경우 imageId는 MyImageId가 된다.
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
