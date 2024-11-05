package com.junebay.plancard.image.vo;

import java.time.LocalDateTime;

/**
 * @author : IAN
 * @date : 2024-11-05
 * @description :
 */
public abstract class Image {

    private String savePath;
    private String fileName;
    private String extension;
    private int size;
    private String alt;
    private boolean isActive;
    private boolean isMain;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
