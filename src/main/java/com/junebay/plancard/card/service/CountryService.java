package com.junebay.plancard.card.service;

import com.junebay.plancard.common.dto.RequestDTO;
import com.junebay.plancard.common.dto.ResponseDTO;

/**
 * @author : IAN
 * @date : 2025-05-15
 * @description : 국가에 대한 비즈니스 로직 처리담당 서비스 인터페이스
 */
public interface CountryService {

    ResponseDTO selectCountries(RequestDTO requestDTO);
}
