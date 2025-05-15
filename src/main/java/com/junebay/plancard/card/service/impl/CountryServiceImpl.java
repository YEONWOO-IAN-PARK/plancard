package com.junebay.plancard.card.service.impl;

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

    @Override
    public ResponseDTO selectCountries(RequestDTO requestDTO) {
        customValidator.validateRequest(requestDTO);
        List<CountryDTO> countryDTOList = countryMapper.selectCountries(requestDTO);

        return setResponseDTO(requestDTO, countryDTOList);
    }

    private ResponseDTO setResponseDTO(RequestDTO requestDTO, List<CountryDTO> countryDTOList) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResult(countryDTOList);
        responseDTO.setPagination(requestDTO.getPagination());
        responseDTO.setDetails(existCountryList);

        return responseDTO;
    }
}
