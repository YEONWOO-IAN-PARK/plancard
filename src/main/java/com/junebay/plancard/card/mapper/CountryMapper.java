package com.junebay.plancard.card.mapper;

import com.junebay.plancard.card.dto.CityDTO;
import com.junebay.plancard.card.dto.CountryDTO;
import com.junebay.plancard.common.dto.RequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : IAN
 * @date : 2025-05-15
 * @description : 국가 정보를 다루는 마이바티스 매퍼 인터페이스
 */
@Mapper
public interface CountryMapper {

    /**
     * 국가DTO 목록을 반환하는 기능
     */
    List<CountryDTO> selectCountries(@Param("requestDTO") RequestDTO requestDTO);

    /**
     * 도시DTO 목록을 반환하는 기능
     */
    List<CityDTO> selectCities(@Param("countryId") String countryId, @Param("requestDTO") RequestDTO requestDTO);
}

