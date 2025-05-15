package com.junebay.plancard.image.service.impl;

import com.junebay.plancard.image.enums.ImageType;
import com.junebay.plancard.image.mapper.ImageMapper;
import com.junebay.plancard.image.service.ImageService;
import com.junebay.plancard.image.vo.CardImage;
import com.junebay.plancard.image.vo.Image;
import com.junebay.plancard.image.vo.MyCardImage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author : IAN
 * @date : 2025-05-12
 * @description : 이미지 서비스 로직을 처리하는 이미지 Impl 클래스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageMapper imageMapper;

    @Override
    public void saveImage(MultipartFile imageFile, Image imageVO, ImageType imageType) throws IOException {

        String savePath = "";

        switch (imageType) {
            case MY_CARD -> {
                MyCardImage myCardImage = (MyCardImage) imageVO;
                savePath = myCardImage.getSavePath();
                log.info("############ MyCardImage Id : {}", myCardImage.getId());
            }
            case CARD -> {
                CardImage cardImage = (CardImage) imageVO;
                savePath = cardImage.getSavePath();
                log.info("############ CardImage Id : {}", cardImage.getId());
            }

            default -> throw new IllegalArgumentException("Unknown Image Type. Type name : " + imageType.name());
        }

        // 저장경로가 존재하지 않을 경우 저장경로 디렉토리를 생성한다.
        File dir = new File(savePath);
        if (!dir.exists()) dir.mkdirs();

        // 저장할 파일 생성
        File outputFile = new File(dir, imageVO.getStoredFileName());

        // .webp 확장자로 저장
        BufferedImage bfImage = ImageIO.read(imageFile.getInputStream());
        if (bfImage == null) throw new IOException("Can't not read Image. Check image info");
        ImageIO.write(bfImage, "webp", outputFile);

        log.info("########### File Save Successfully: {}", outputFile.getAbsolutePath());
    }

    @Override
    public Image insertImage(Image imageVO, ImageType imageType) {

        switch (imageType) {
//            case CARD -> {}
            case MY_CARD -> {
                MyCardImage myCardImage = (MyCardImage) imageVO;
                imageMapper.insertMyCardImage(myCardImage);
                return myCardImage;
            }
//            case COUNTRY -> {}
//            case CITY -> {}
            default -> throw new IllegalArgumentException("Unknown Image Type. Type name : " + imageType.name());
        }
    }

    @Override
    public void deleteImage(long myCardImageId) {
        imageMapper.deleteImage(myCardImageId);
    }
}
