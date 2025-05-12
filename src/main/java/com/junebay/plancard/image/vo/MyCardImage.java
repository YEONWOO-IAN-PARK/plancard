package com.junebay.plancard.image.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author : IAN
 * @date : 2024-11-05
 * @description :
 */
@Getter
@Setter
public class MyCardImage extends Image {

    private long id;
    private long myCardId;
}
