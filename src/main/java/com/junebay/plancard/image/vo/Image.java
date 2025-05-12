package com.junebay.plancard.image.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author : IAN
 * @date : 2024-11-05
 * @description : 이미지 추상 클래스
 */
@Getter
@Setter
public abstract class Image {

    private String savePath;
    private String originalFileName;
    private String storedFileName;
    private String extension;
    private long size;
    private String alt;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
