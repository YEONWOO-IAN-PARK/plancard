package com.junebay.plancard.card.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : IAN
 * @date : 2025-05-15
 * @description : 클라이언트에서 요구하는 국가 정보 DTO
 */
@Getter
@Setter
public class CountryDTO {

    private String countryId;
    private String title;
    private String description;
    private String imageUrl;
}
