package com.junebay.plancard.card.service;

import com.junebay.plancard.common.dto.RequestDTO;
import com.junebay.plancard.common.dto.ResponseDTO;

/**
 * @author : IAN
 * @date : 2025-05-15
 * @description : 국가에 대한 비즈니스 로직 처리담당 서비스 인터페이스
 */
public interface CountryService {

    /**
     * 국가 목록이 담긴 응답 DTO를 반환하는 기능
     */
    ResponseDTO selectCountries(RequestDTO requestDTO);

    /**
     * 도시 목록이 담긴 응답 DTO를 반환하는 기능
     */
    ResponseDTO selectCities(String countryId, RequestDTO requestDTO);
}
