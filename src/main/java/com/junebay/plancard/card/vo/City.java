package com.junebay.plancard.card.vo;

import java.time.LocalDateTime;

/**
 * @author : IAN
 * @date : 2024-11-05
 * @description :
 */
public class City {
    private String id;
    private String name;
    private String nameAscii;
    private String description;
    private double latitude;
    private double longitude;
    private String countryId;
    private long mainImageId;   // City Image Id
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
