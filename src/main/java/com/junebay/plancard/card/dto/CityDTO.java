package com.junebay.plancard.card.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : IAN
 * @date : 2025-05-16
 * @description : 클라이언트에서 요구하는 도시 정보 DTO
 */
@Getter
@Setter
public class CityDTO extends CountryDTO{

    private String cityId;
}
