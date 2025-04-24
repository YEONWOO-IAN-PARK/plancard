package com.junebay.plancard.card.dto;

import com.junebay.plancard.image.vo.MyCardImage;
import lombok.Getter;

import java.util.List;

/**
 * @author : IAN
 * @date : 2025-04-16
 * @description :
 */
@Getter
public class MyCardDTO extends CardDTO {

    private long myCardId;
    private List<MyCardImage> myImageList;
}
