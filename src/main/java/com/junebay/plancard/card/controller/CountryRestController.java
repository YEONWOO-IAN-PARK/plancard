package com.junebay.plancard.card.controller;

import com.junebay.plancard.card.service.CountryService;
import com.junebay.plancard.common.dto.SearchDTO;
import com.junebay.plancard.common.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : IAN
 * @date : 2025-05-14
 * @description : 국가 또는 도시 관련 RestController 클래스
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/countries")
@RestController
public class CountryRestController {

    private final CountryService countryService;

    /**
     * 국가 조회 요청핸들러메서드
     */
    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> countries(@RequestBody SearchDTO searchDTO) {
        ResponseDTO responseDTO = countryService.selectCountries(searchDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{countryId}/cities/search")
    public ResponseEntity<ResponseDTO> cities(@PathVariable String countryId, @RequestBody SearchDTO searchDTO) {
        ResponseDTO responseDTO = countryService.selectCities(countryId, searchDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
