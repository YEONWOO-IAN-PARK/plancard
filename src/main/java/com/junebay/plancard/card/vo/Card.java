package com.junebay.plancard.card.vo;

import java.time.LocalDateTime;

/**
 * @author : IAN
 * @date : 2024-11-05
 * @description :
 */
public class Card {

    private long id;
    private String title;
    private String description;
    private String countryId;
    private String cityId;
    private long categoryId;
    private int rating;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
