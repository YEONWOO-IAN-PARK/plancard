package com.junebay.plancard.card.vo;

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
public class City {
    private String id;
    private String name;
    private String nameAscii;
    private String description;
    private double latitude;
    private double longitude;
    private String countryId;
    private long mainImageId;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
