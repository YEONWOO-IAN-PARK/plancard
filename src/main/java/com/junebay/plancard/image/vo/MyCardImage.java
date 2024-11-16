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
    private boolean isDefault;  // my card의 'card id'로 찾은 기본 카드의 메인 이미지를 사용하는지 유무
}
