package com.junebay.plancard.image.service;

import com.junebay.plancard.image.enums.ImageType;
import com.junebay.plancard.image.vo.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author : IAN
 * @date : 2025-05-12
 * @description : 이미지 관련 서비스 인터페이스
 */
public interface ImageService {

    void saveImage(MultipartFile imageFile, Image imageVO, ImageType imageType) throws IOException;

    Image insertImage(Image imageVO, ImageType imageType);

    void deleteImage(long myCardImageId);
}
