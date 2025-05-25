package com.junebay.plancard.card.mapper;

import com.junebay.plancard.card.dto.CityDTO;
import com.junebay.plancard.card.dto.CountryDTO;
import com.junebay.plancard.common.dto.SearchDTO;
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
     * 국가DTO 목록의 총 건수를 반환하는 기능
     */
    int selectAllCountriesCount(@Param("searchDTO") SearchDTO searchDTO);

    /**
     * 국가DTO 목록을 반환하는 기능
     */
    List<CountryDTO> selectCountries(@Param("searchDTO") SearchDTO searchDTO);

    /**
     * 도시DTO 목록의 총 건수를 반환하는 기능
     */
    int selectAllCitiesCount(@Param("countryId") String countryId, @Param("searchDTO") SearchDTO searchDTO);
    
    /**
     * 도시DTO 목록을 반환하는 기능
     */
    List<CityDTO> selectCities(@Param("countryId") String countryId, @Param("searchDTO") SearchDTO searchDTO);

 
}

