package com.junebay.plancard.card.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
public class BasicCardDTO {

    private long cardId;
    private String title;
    private String description;
    private String country;
    private String city;
    private String category;
    private int rating;
    private boolean scrap;
}
