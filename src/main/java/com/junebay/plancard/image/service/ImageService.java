package com.junebay.plancard.image.service;

import com.junebay.plancard.image.ImageType;
import com.junebay.plancard.image.vo.Image;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : IAN
 * @date : 2025-05-12
 * @description : 이미지 관련 서비스 인터페이스
 */
public interface ImageService {

    void saveImage(MultipartFile imageFile, String savePath);

    void insertImage(Image imageVO, ImageType imageType);
}
