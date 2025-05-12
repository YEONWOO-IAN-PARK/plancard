package com.junebay.plancard.image.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : IAN
 * @date : 2025-04-16
 * @description : 사용자 요청 DTO에 매핑될 내카드이미지 DTO
 */
@Getter
@Setter
public class MyCardImageDTO {

    private long myImageId;
    private long myCardId;
    private String filename;    // 원본파일명(original_file_name)
    private String filepath;    // 파일저장경로(save_path)
    private String imageUrl;
    private String alt;
}
