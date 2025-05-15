package com.junebay.plancard.image.mapper;

import com.junebay.plancard.image.vo.MyCardImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : IAN
 * @date : 2025-05-12
 * @description : 이미지 관련 DB작업을 담당하는 MyBatis 인터페이스
 */
@Mapper
public interface ImageMapper {

    /**
     * 내카드 이미지 VO를 전달받아, DB에 해당 정보를 저장하는 기능
     * @param myCardImage
     */
    void insertMyCardImage(MyCardImage myCardImage);

    /**
     * 내카드 이미지 ID를 전달받아, DB에 해당 정보를 삭제하는 기능
     * @param myCardImageId
     */
    void deleteImage(@Param("myCardImageId") long myCardImageId);

    /**
     * 내카드 이미지 ID를 전달받아, DB에 해당 정보를 소프트 딜리트 하는 기능
     */
    void deleteMyCardImage(@Param("myCardImageId") long myCardImageId);
}
