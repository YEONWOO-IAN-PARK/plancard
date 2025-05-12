package com.junebay.plancard.image.mapper;

import com.junebay.plancard.image.vo.MyCardImage;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : IAN
 * @date : 2025-05-12
 * @description : 이미지 관련 DB작업을 담당하는 MyBatis 인터페이스
 */
@Mapper
public interface ImageMapper {

    void insertMyCardImage(MyCardImage myCardImage);
}
