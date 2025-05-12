package com.junebay.plancard.image.service.impl;

import com.junebay.plancard.image.ImageType;
import com.junebay.plancard.image.mapper.ImageMapper;
import com.junebay.plancard.image.service.ImageService;
import com.junebay.plancard.image.vo.Image;
import com.junebay.plancard.image.vo.MyCardImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : IAN
 * @date : 2025-05-12
 * @description : 이미지 서비스 로직을 처리하는 이미지 Impl 클래스
 */
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageMapper imageMapper;

    @Override
    public void saveImage(MultipartFile imageFile, String savePath) {

    }

    @Override
    public void insertImage(Image imageVO, ImageType imageType) {

        switch (imageType) {
//            case CARD -> {}
            case MY_CARD -> {
                imageMapper.insertMyCardImage((MyCardImage) imageVO);
            }
//            case COUNTRY -> {}
//            case CITY -> {}
            default -> throw new IllegalArgumentException("Unknown Image Type. Type name : " + imageType.name());
        }
    }
}
