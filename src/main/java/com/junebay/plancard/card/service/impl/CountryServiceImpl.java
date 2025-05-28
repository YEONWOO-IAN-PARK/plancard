package com.junebay.plancard.card.service.impl;

import com.junebay.plancard.card.dto.CityDTO;
import com.junebay.plancard.card.dto.CountryDTO;
import com.junebay.plancard.card.mapper.CountryMapper;
import com.junebay.plancard.card.service.CountryService;
import com.junebay.plancard.common.dto.SearchDTO;
import com.junebay.plancard.common.dto.ResponseDTO;
import com.junebay.plancard.common.validator.CustomValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : IAN
 * @date : 2025-05-15
 * @description : 국가에 대한 비즈니스 로직 처리담당 서비스 클래스
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {

    private final CustomValidator customValidator;
    private final CountryMapper countryMapper;

    @Value("${response.ok.exist.country.list.detail}") private String existCountryList;
    @Value("${response.ok.exist.city.list.detail}") private String existCityList;
    @Value("${response.ok.notExist.list.detail}") private String notExistList;

    @Override
    public ResponseDTO selectCountries(SearchDTO searchDTO) {
        int totalItemCount;

        customValidator.validateRequest(searchDTO);

        totalItemCount = countryMapper.selectAllCountriesCount(searchDTO);
        searchDTO.getPagination().setTotalItems(totalItemCount);

        List<CountryDTO> countryDTOList = countryMapper.selectCountries(searchDTO);

        return setResponseDTO(searchDTO, countryDTOList);
    }

    @Override
    public ResponseDTO selectCities(String countryId, SearchDTO searchDTO) {
        int totalItemCount;

        customValidator.validateRequest(searchDTO);

        totalItemCount = countryMapper.selectAllCitiesCount(countryId, searchDTO);
        searchDTO.getPagination().setTotalItems(totalItemCount);

        List<CityDTO> dtoList = countryMapper.selectCities(countryId, searchDTO);

        return setResponseDTO(searchDTO, dtoList);
    }

    /**
     * 국가 또는 도시 목록이 포함된 응답DTO 세팅
     */
    private ResponseDTO setResponseDTO(SearchDTO searchDTO, List<?> dtoList) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResult(dtoList);
        responseDTO.setPagination(searchDTO.getPagination());

        boolean isEmpty = dtoList.isEmpty();
        String details;

        if (isEmpty) {
            details = notExistList;
        } else {
            boolean isCityDTO = dtoList.get(0) instanceof CityDTO;
            details = isCityDTO ? existCityList : existCountryList;
        }
        responseDTO.setDetails(details);


        return responseDTO;
    }

}
