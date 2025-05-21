package com.junebay.plancard.card.service.impl;

import com.junebay.plancard.card.dto.CityDTO;
import com.junebay.plancard.card.dto.CountryDTO;
import com.junebay.plancard.card.mapper.CountryMapper;
import com.junebay.plancard.card.service.CountryService;
import com.junebay.plancard.common.dto.RequestDTO;
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
    public ResponseDTO selectCountries(RequestDTO requestDTO) {
        int totalItemsCount;

        customValidator.validateRequest(requestDTO);

        totalItemsCount = countryMapper.selectAllCountriesCount(requestDTO);
        requestDTO.getPagination().setTotalItems(totalItemsCount);

        List<CountryDTO> countryDTOList = countryMapper.selectCountries(requestDTO);


        return setResponseDTO(requestDTO, countryDTOList);
    }

    @Override
    public ResponseDTO selectCities(String countryId, RequestDTO requestDTO) {
        int totalItemsCount;

        customValidator.validateRequest(requestDTO);

        totalItemsCount = countryMapper.selectAllCitiesCount(countryId, requestDTO);
        requestDTO.getPagination().setTotalItems(totalItemsCount);

        List<CityDTO> dtoList = countryMapper.selectCities(countryId, requestDTO);

        return setResponseDTO(requestDTO, dtoList);
    }

    /**
     * 국가 또는 도시 목록이 포함된 응답DTO 세팅
     */
    private ResponseDTO setResponseDTO(RequestDTO requestDTO, List<?> dtoList) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResult(dtoList);
        responseDTO.setPagination(requestDTO.getPagination());

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
