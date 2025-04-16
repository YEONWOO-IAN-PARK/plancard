package com.junebay.plancard.card.dto;

import com.junebay.plancard.image.dto.MyCardImageDTO;

import java.util.List;

/**
 * @author : IAN
 * @date : 2025-04-16
 * @description :
 */
public class MyCardDTO extends CardDTO {

    private long myCardId;
    private List<MyCardImageDTO> myImageList;
}
